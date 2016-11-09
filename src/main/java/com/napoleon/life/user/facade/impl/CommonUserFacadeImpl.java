package com.napoleon.life.user.facade.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.napoleon.life.common.util.CryptUtil;
import com.napoleon.life.common.util.DateStyle;
import com.napoleon.life.common.util.DateUtil;
import com.napoleon.life.common.util.StringUtil;
import com.napoleon.life.exception.CommonResultCode;
import com.napoleon.life.framework.enums.LoginSourceEnum;
import com.napoleon.life.framework.redis.RedisServer;
import com.napoleon.life.framework.result.CommonRltUtil;
import com.napoleon.life.user.bean.CommonUser;
import com.napoleon.life.user.constants.Constants;
import com.napoleon.life.user.dto.UserLoginDto;
import com.napoleon.life.user.dto.UserRegisterDto;
import com.napoleon.life.user.enums.ActivateStatusEnum;
import com.napoleon.life.user.enums.UserStatusEnum;
import com.napoleon.life.user.facade.CommonUserFacade;
import com.napoleon.life.user.service.CommonSerialNoService;
import com.napoleon.life.user.service.CommonUserService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


@Service
public class CommonUserFacadeImpl implements CommonUserFacade {

	private static final Logger logger = LoggerFactory.getLogger(CommonUserFacadeImpl.class);
	
	@Autowired
	private CommonUserService userService;
	
	@Autowired
	private CommonSerialNoService serialNoService;

	@Autowired
	private RedisServer redisServer;
	
	@Value("${secure.app.key}")
	private String secureAppKey;
	
	@Value("${alidayu.mobile.code.url}")
	private String alidayuMobileCodeUrl;
	
	@Value("${alidayu.app.key}")
	private String alidayuAppKey;
	
	@Value("${alidayu.app.secret}")
	private String alidayuAppSecret;
	
	@Value("${alidayu.mobile.code.template}")
	private String alidayuMobileCodeTemplate;
	
	@Value("${redis.mobile.code.expire}")
	private String redisMobileCodeExpire;
	
	@Value("${redis.global.login.expire}")
	private String globalLoginRedisExpire;
	
	@Value("${redis.wonderfull.life.access_token.expire}")
	private String wLifeAccessTokenRedisExpire;
	
	@Override
	public String getPhoneCode(String phone){
		Map<String, String> map = this.redisServer.getHashAll(Constants.PHONE_CODE + phone, null);
		Date currentDate = new Date();
		if(map != null && !map.isEmpty()){
			String createdTime = map.get("created_time");
			
			if(StringUtil.notEmpty(createdTime)){
				// 如果mobileCode的创建时间大于当前时间120秒，则再次生成验证码，如果小于等于120秒，则提示120秒之后在生成验证码
				Date createdDate = DateUtil.addSecond(DateUtil.StringToDate(createdTime, DateStyle.YYYY_MM_DD_HH_MM_SS), 120);
				if(DateUtil.isAfter(createdDate, currentDate)){
					return CommonRltUtil.createCommonRltToString(CommonResultCode.ACCOUNT_HAS_ACTIVATE);
				}
			}
		}
		
		TaobaoClient client = new DefaultTaobaoClient(alidayuMobileCodeUrl, alidayuAppKey, alidayuAppSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("精彩生活");
		
		JSONObject smsParam = new JSONObject();
		String mobileCode = StringUtil.getRandomNum(6);
		smsParam.put("code", mobileCode);
		req.setSmsParamString(smsParam.toJSONString());
		
		req.setRecNum(phone);
		req.setSmsTemplateCode(alidayuMobileCodeTemplate);
		
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			map = new HashMap<String, String>();
			map.put("created_time", DateUtil.DateToString(currentDate, DateStyle.YYYY_MM_DD_HH_MM_SS));
			map.put("mobile_code", mobileCode);
			//this.redisServer.setHashMap(Constants.PHONE_CODE + phone, map, Integer.valueOf(redisMobileCodeExpire));
			this.redisServer.setHashMap(Constants.PHONE_CODE + phone, map, null);
			
			map.put("result_msg", rsp.getBody());
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS, map);
		} catch (ApiException e) {
			return CommonRltUtil.createCommonRltToString(CommonResultCode.ACCOUNT_HAS_ACTIVATE);
		}
	}
	
	@Override
	public String register(UserRegisterDto registerInfo) {
		// 1. 验证手机验证码
		String phoneCode = this.redisServer.getHash(Constants.PHONE_CODE + registerInfo.getPhone(), "mobile_code", null);
		if(StringUtil.isEmpty(phoneCode)){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.PHONE_CODE_EXPIRED); 
		}else if(!registerInfo.getPhoneCode().equals(phoneCode)){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.PHONE_CODE_WRONG); 
		}
		
		// 2. 查询该email是否被注册过,如果被注册过，且是激活状态，则提示用户已经注册，请登陆
		CommonUser userInfo = this.userService.findByPhoneNumber(registerInfo.getPhone());
		if (userInfo != null && ActivateStatusEnum.ACTIVATE_YES.getCode().equals(userInfo.getActivateStatus())) {
			return CommonRltUtil.createCommonRltToString(CommonResultCode.PHONE_NUMBER_HAS_REGISTER);
		}
		
		userInfo = new CommonUser();//[email, sex, address]
		userInfo.setUserNo(serialNoService.getSerialNo(Constants.USER_NO));
		userInfo.setUserName(registerInfo.getUserName());
		userInfo.setPassword(CryptUtil.encrypt(registerInfo.getPassword(), this.secureAppKey));
		userInfo.setPhone(registerInfo.getPhone());
		userInfo.setActivateStatus(ActivateStatusEnum.ACTIVATE_YES.getCode());
		userInfo.setStatus(UserStatusEnum.STATUS_NORMAL.getCode());
		userInfo.setCreateDate(new Timestamp(new Date().getTime()));
		userInfo.setUpdateTime(new Timestamp(new Date().getTime()));
		this.userService.insert(userInfo);
		
		this.redisServer.del(Constants.PHONE_CODE + registerInfo.getPhone());
		
		return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS);
	}
	
	@Override
	public String login(UserLoginDto loginInfo) {
		CommonUser userInfo = this.userService.findByPhoneNumber(loginInfo.getPhoneNumber());
		
		// 如果用户不存在
		if(userInfo == null){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.ACCOUNT_NOT_EXIST);
		}
		
		String cryptPassword = CryptUtil.encrypt(loginInfo.getPassword(), this.secureAppKey);
		// 如果密码不正确
		if(!cryptPassword.equals(userInfo.getPassword())){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.PASSWORD_WRONG);
		}
		
		// 如果账号还没有被激活
		if(ActivateStatusEnum.ACTIVATE_NO.getCode().equals(userInfo.getActivateStatus())){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.ACCOUNT_NOT_ACTIVATE);
		}
		
		// 如果用户状态不可用［冻结，注销］
		if(!UserStatusEnum.STATUS_NORMAL.getCode().equals(userInfo.getStatus())){
			return CommonRltUtil.createCommonRltToString(CommonResultCode.ACCOUNT_STATUS_INVALID);
		}
		
		// 首先遍历所有的该用户的access_token，删除失效的access_token
		Map<String, String> allAccessToken = this.redisServer.getHashAll(Constants.GLOBAL_REDIS_USER_ID  + userInfo.getUserNo(), null);
		if(allAccessToken != null && !allAccessToken.isEmpty()){
			for(Map.Entry<String, String> accessTokenEntry : allAccessToken.entrySet()){
				String json = this.redisServer.get(accessTokenEntry.getKey(), null);
				if(StringUtil.isEmpty(json)){
					this.redisServer.delHashField(Constants.GLOBAL_REDIS_USER_ID + userInfo.getUserNo(), accessTokenEntry.getKey());
				}
			}
		}
		
		// 生成access_token
		JSONObject user = this.createUserInfo(userInfo);
		String accessToken = this.serialNoService.getSerialNo(loginInfo.getSource() + ".");
		
		if(LoginSourceEnum.LOGIN_SOURCE_WLIFE.getCode().equals(loginInfo.getSource())){
			this.redisServer.setHash(Constants.GLOBAL_REDIS_USER_ID + userInfo.getUserNo(), accessToken, "", Integer.valueOf(this.globalLoginRedisExpire));
			this.redisServer.set(accessToken, user.toJSONString(), Integer.valueOf(this.wLifeAccessTokenRedisExpire));
			Map<String, String> result = new HashMap<String, String>();
			result.put("access_token", accessToken);
			return CommonRltUtil.createCommonRltToString(CommonResultCode.SUCCESS, result);
		}else{
			return CommonRltUtil.createCommonRltToString(CommonResultCode.REJECT_SOURCE_LOGIN);
		}
	}
	
	
	private JSONObject createUserInfo(CommonUser user){
		JSONObject userInfo = new JSONObject();
		userInfo.put("id", user.getId());
		userInfo.put("user_no", user.getUserNo());
		userInfo.put("user_name", user.getUserName());
		userInfo.put("phone", user.getPhone());
		userInfo.put("activate_status", user.getActivateStatus());
		userInfo.put("status", user.getStatus());
		
		if(StringUtil.notEmpty(user.getEmail())){
			userInfo.put("email", user.getEmail());
		}
		if(StringUtil.notEmpty(user.getSex())){
			userInfo.put("sex", user.getSex());
		}
		if(StringUtil.notEmpty(user.getAddress())){
			userInfo.put("address", user.getAddress());
		}
		if(StringUtil.notEmpty(user.getHeaderImg())){
			userInfo.put("header_img", user.getHeaderImg());
		}
		
		return userInfo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
