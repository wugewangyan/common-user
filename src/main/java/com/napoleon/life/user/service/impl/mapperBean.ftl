<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${template.javaBeanFullName}">
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
	
	<update id="updateBySelector" parameterType="${template.javaBeanFullName}">
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
	
	<update id="update" parameterType="${template.javaBeanFullName}">
		${template.update}
	</update>
	
	<delete id="delete" parameterType="java.lang.String">
		${template.delete}
	</delete>
	
	<select id="read" parameterType="java.lang.String" resultMap="BaseResultMap">
		${template.findById}
	</select>
	 
	 
	<select id="list" resultMap="BaseResultMap">
		${template.getAll}
	</select>
	
	<insert id="insertBatch" parameterType="${template.javaBeanFullName}">
		${template.batchInsertPre}
		VALUES
		<foreach collection="list" item="item" index="index" separator=",">
			${template.batchInsertAfter}
		</foreach>
	</insert>
	
</mapper>