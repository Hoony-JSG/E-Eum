package com.craypas.bottle.model.repository;

import static com.craypas.bottle.model.entity.QReqBottle.reqBottle;
import static com.craypas.bottle.model.entity.QResBottle.*;
import static com.craypas.bottle.model.entity.QUserBottle.*;
import static com.querydsl.core.group.GroupBy.*;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.craypas.bottle.model.dto.response.DetailReqBottleDto;
import com.craypas.bottle.model.dto.response.QDetailReqBottleDto;
import com.craypas.bottle.model.dto.response.QResBottleDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DetailReqBottleRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public DetailReqBottleDto findDetailReqBottle(long id) {
		Map<Long, DetailReqBottleDto> resultMap = jpaQueryFactory
			.from(reqBottle)
			.leftJoin(reqBottle.userBottles, userBottle).on(userBottle.bottleId.eq(reqBottle.id))
			.leftJoin(userBottle.resBottles, resBottle).on(resBottle.userBottleId.eq(userBottle.id))
			.where(reqBottle.id.eq(3L))
			.transform(groupBy(reqBottle.id).as(new QDetailReqBottleDto(
				reqBottle.id, reqBottle.content, reqBottle.type, reqBottle.sentiment, reqBottle.regTime, reqBottle.status,
				list(new QResBottleDto(resBottle.id, resBottle.content, resBottle.regTime, resBottle.status))
			)));

		List<DetailReqBottleDto> aaa = resultMap.keySet().stream()
			.map(resultMap::get)
			.collect(toList());
		System.out.println(aaa.get(0));
		return null;
	}
}
