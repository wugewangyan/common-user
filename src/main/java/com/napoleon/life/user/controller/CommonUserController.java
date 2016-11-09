package com.napoleon.life.user.controller;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.napoleon.life.common.util.RSAUtils;
import com.napoleon.life.framework.base.BaseController;
import com.napoleon.life.framework.resolver.ParamValid;
import com.napoleon.life.user.dto.UserLoginDto;
import com.napoleon.life.user.dto.UserPhoneCodeDto;
import com.napoleon.life.user.dto.UserRegisterDto;
import com.napoleon.life.user.facade.CommonUserFacade;

@Controller
@RequestMapping("/common/user")
public class CommonUserController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUserController.class);

	@Autowired
	private CommonUserFacade lifeUserFacade;

	@Autowired
	private Producer captchaProducer;
	
	
	/**
	 * 获取手机验证码
	 * @param phoneInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_phone_code", method = RequestMethod.GET)
    public String getPhoneCode(@ParamValid UserPhoneCodeDto phoneInfo) {
		return this.lifeUserFacade.getPhoneCode(phoneInfo.getPhone());
    }
	
	
	/**
	 * 注册
	 * @param registerInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ParamValid UserRegisterDto registerInfo) {
		return this.lifeUserFacade.register(registerInfo);
    }
	
	
	/**
	 * 登陆
	 * @param loginInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ParamValid UserLoginDto loginInfo) {
		return this.lifeUserFacade.login(loginInfo);
    }
	
	
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captcha-image")  
    public ModelAndView captchaImage(HttpServletRequest request,
    		HttpServletResponse response) throws Exception {  
   
        response.setDateHeader("Expires", 0); 
 
        // Set standard HTTP/1.1 no-cache headers. 
 
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader). 
 
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        // Set standard HTTP/1.0 no-cache header. 
 
        response.setHeader("Pragma", "no-cache");  
        // return a jpeg 
 
        response.setContentType("image/jpeg");  
        // create the text for the image
  
        String capText = captchaProducer.createText();  
        // store the text in the session 
 
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        // create the image with the text  

        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        // write the data out  

        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    } 
	
	/**
	 * 获取非对称加密信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getKeyPair")  
	public Map<String, String> getKeyPair(){
		Map<String, String> result = new HashMap<String, String>();
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
		
		String publicExponent = new String(Hex.encodeHex(publicKey.getPublicExponent().toByteArray()));
		String publicModulus = new String(Hex.encodeHexString(publicKey.getModulus().toByteArray()));
		
		result.put("publicExponent", publicExponent);
		result.put("publicModulus", publicModulus);
		return result;
	}
}
