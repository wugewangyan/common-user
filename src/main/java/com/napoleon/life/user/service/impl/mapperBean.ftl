<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${template.nameSpace}">
	<resultMap id="BaseResultMap" type="${template.javaBeanFullName}">
	    <id column="${template.pkName}" property="${template.pkJavaName}" jdbcType="${template.pkJdbcType}"/>
	    <#list template.updateEntrys as entry>
			<result column="${entry.column}" property="${entry.javaColumn}" jdbcType="${entry.jdbcType}"/>
	    </#list>
  	</resultMap>
  	
  	<sql id = "Base_Column_List">
		${template.baseColumnList}
	</sql>
	
	<insert id="insert" parameterType="${template.javaBeanFullName}">
		${template.insert}
	</insert>
	
	<update id="update" parameterType="${template.javaBeanFullName}">
		update ${template.tableName}
		 <set>
	 	<#list template.updateEntrys as entry>
			<if test="${entry.javaColumn} != null" >
		        ${entry.column} = ${entry.updateJavaColumn},
		    </if>
	    </#list>
		</set>
		
		 where ${template.pkName} = ${template.pkNameUpdate}
	</update>
	
	<delete id="delete" parameterType="java.lang.Long">
		${template.delete}
	</delete>
	
	<select id="get" parameterType="java.lang.Long" resultMap="BaseResultMap">
		${template.findById}
	</select>
	 
	 
	<select id="list" resultMap="BaseResultMap">
		${template.getAll}
	</select>
	
	<select id="findBy${template.entityNoToQueryUpcase}" resultMap="BaseResultMap" parameterType="java.util.Map">
		${template.getByEntityNo}
	</select>
	
</mapper>