<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packetNameMapper}.${mapperName}">

	<resultMap id="poMap" type="${packetNamePo}.${poName}">
		#foreach ($field in $fieldsByTable)
		<result column="${field.name}" property="${field.javaFieldName}"/>
		#end
	</resultMap>

	<!-- 表所有参数-->
	<sql id="tableCol">
		#foreach ($field in $fieldsByTable)
		`${field.name}`#if($foreach.hasNext),#end
		#end
	</sql>

	<!-- 实体所有参数-->
	<sql id="poVal">
		#foreach ($field in $fieldsByTable)
		#{val.${field.javaFieldName}}#if($foreach.hasNext),#end
		#end
	</sql>

	<sql id="where">
		<where>
			#foreach ($field in $fieldsByTable)
			<if test="val.${field.javaFieldName}!=null">and ${field.name} = #{val.${field.javaFieldName}}}</if>
			#end
		</where>
	</sql>

	<insert id="insert" parameterType="${packetNamePo}.${poName}">
		<if test="val!=null">
			INSERT INTO ${tableName}(<include refid="tableCol" />)
			VALUES(<include refid="poVal"/>)
		</if>
	</insert>

	<insert id="insertOrUpdate" parameterType="${packetNamePo}.${poName}">
		<if test="val!=null">
			INSERT INTO ${tableName}(<include refid="tableCol" />)
			VALUES(<include refid="poVal"/>)
			ON DUPLICATE KEY UPDATE
			#foreach ($field in $fieldsByTable)
			${field.name} = values(${field.name})#if($foreach.hasNext),#end
			#end
		</if>
	</insert>

	<insert id="insertList" parameterType="list">
		<if test="val!=null">
			INSERT INTO ${tableName}(<include refid="tableCol" />)
			VALUES
			<foreach collection="data" item="val" index="index" separator=",">
				(<include refid="poVal"/>)
			</foreach>
		</if>
	</insert>

	<delete id="removeBy">

	</delete>

	<select id="getBy">

	</select>

	<update id="updateBy">

	</update>

	<select id="getOne" parameterType="${packetNamePo}.${poName}" resultMap="poMap" >
		SELECT <include refid="tableCol"/> FROM ${tableName}
		<include refid="where"/> LIMIT 1
	</select>

	<select id="getMany" parameterType="${packetNamePo}.${poName}" resultMap="poMap">
		SELECT <include refid="tableCol"/> FROM ${tableName}
		<include refid="where"/>
	</select>

</mapper>