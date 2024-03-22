<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${basePackage}.dao.I${upperClassName}Dao">


	<resultMap id="poMap" type="${basePackage}.model.po.${upperClassName}">
		${poMap}
	</resultMap>		


	<sql id="tableColumns">
		${tableColumns}
	</sql>
    

	<sql id="tableColumnsWithT">
		${tableColumnsWithT}
	</sql>


	<sql id="poValues">
		${poValues}
	</sql>


	<sql id="poClause">
		<where>
			1 = 1
			${poClause}
		</where>
	</sql>
	

	<insert id="add" parameterType="${basePackage}.model.po.${upperClassName}">
		<if test="values!=null">
			insert into ${tableName}
			(
			<include refid="tableColumns" />
			)
			values
			(
			<include refid="poValues" />
			)
		</if>
	</insert>
	

	<insert id="saveOrUpdate" parameterType="${basePackage}.model.po.${upperClassName}">
		<if test="values!=null">
			insert into ${tableName}
			(
			<include refid="tableColumns" />
			)
			values
			(
			<include refid="poValues" />
			)
			${duplicateKey}
		</if>
	</insert>
	

	<insert id="addList" parameterType="list">
		insert into ${tableName}
		(
		<include refid="tableColumns" />
		)
		values
		<foreach collection="data" item="values" index="index"
			separator=",">
			(
			<include refid="poValues" />
			)
		</foreach>
	</insert>
	

	<insert id="saveOrUpdateList" parameterType="list">
		insert into ${tableName}
		(
		<include refid="tableColumns" />
		)
		values
		<foreach collection="data" item="values" index="index"
			separator=",">
			(
			<include refid="poValues" />
			)
		</foreach>
		${duplicateKey}
	</insert>
		

	<delete id="delByPrimaryKey" parameterType="${priDataType}">
		<if test="${priFormatColumnName}!=null">
			delete from ${tableName}
			where ${priColumnName} = #{${priFormatColumnName}}
		</if>
	</delete>
	

	<delete id="delList" parameterType="list">
		<if test="list != null">
			delete from ${tableName} where ${priColumnName} in
			<foreach item="item" collection="list" open="(" separator=","
				close=")">
				#{item}
			</foreach>
		</if>
	</delete>
			

	<select id="getByPrimaryKey" parameterType="${priDataType}" resultMap="poMap">
		select
		<include refid="tableColumnsWithT" />
		from ${tableName} as t
		where ${priColumnName} = #{${priFormatColumnName}}
	</select>
	

	<select id="getByPrimaryKeys" parameterType="list" resultMap="poMap">
		<if test="list != null">
			select 
			<include refid="tableColumnsWithT" />
			from ${tableName} as t 
			where ${priColumnName} in
			<foreach item="item" collection="list" open="(" separator=","
				close=")">
				#{item}
			</foreach>
		</if>
	</select>
	

	<update id="updateByPrimaryKey" parameterType="${basePackage}.model.po.${upperClassName}">
		<if test="record != null and record.${priFormatColumnName} !=null">
			${updateByPrimaryKey} 
		</if>
	</update>
		

	<select id="get${upperClassName}" parameterType="map"
		resultMap="poMap">
		<if test="where != null">
			select
			<include refid="tableColumnsWithT" />
			from ${tableName} as t

			<include refid="poClause" />
			limit 1
		</if>
	</select>
	

	<select id="getList" parameterType="map"
		resultMap="poMap">
		select
		<include refid="tableColumnsWithT" />
		from ${tableName} as t
		<if test="where != null">

		    <include refid="poClause" />
		</if>
		<if
			test="limitStart != null and limitStart >= 0 and count !=null and count >0">
			limit ${limitStart} , ${count}
		</if>
	</select>
	

	<select id="count" parameterType="${basePackage}.model.po.${upperClassName}" resultType="${priDataType}">
		select count(1) from ${tableName} as t
		<if test="where != null">
		    <include refid="poClause" />
		</if>
	</select>
		
</mapper>