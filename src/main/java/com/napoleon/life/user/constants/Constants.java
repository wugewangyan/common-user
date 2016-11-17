package com.napoleon.life.user.constants;

public class Constants {

	/**
	 * 生成手机验证码
	 */
	public static final String PHONE_CODE = "phone_code_";

	/**
	 * 生成用户名的前缀
	 */
	public static final String USER_NO = "wf_user_";

	/**
	 * 登陆时全局的redis access_token hash key
	 */
	public static final String GLOBAL_REDIS_USER_ID = "token.user_id.";

	// insert
	public static final String INSERT_PRE_SQL = " INSERT INTO ";
	public static final String INSERT_VALUES_SQL = " VALUES( ";

	// update
	public static final String UPDATE_PRE_SQL = " UPDATE ";
	public static final String UPDATE_SET_SQL = " SET ";
	public static final String UPDATE_PARAMS_FALG = " = ?, ";

	// delete
	public static final String DELETE_PRE_SQL = " DELETE FROM ";

	// select
	public static final String SELECT_PRE_SQL = " SELECT ";

	// 分页
	public static final String PAGING_PRE_SQL = " select * from ( select row_.*, rownum rownum_ from ( ";
	public static final String PAGING_END_SQL = " ) row_ where rownum <= ?) where rownum_ > ? ";
	public static final String PAGEING_MYSQL = " LIMIT ?, ? ";
	public static final String PAGING_COUNT_SQL = " select count(*) from ( ";

	// Common
	public static final String COMMA = ", ";
	public static final String LEFT_BRACES = " ( ";
	public static final String RIGHT_BRACES = " ) ";
	public static final String PARAMS_FLAG = "?";
	public static final String WHERE_SQL = " WHERE ";
	public static final String FROM_SQL = " FROM ";
	public static final String EQUAL_PARAMS_FALG = " = ? ";
	
}
