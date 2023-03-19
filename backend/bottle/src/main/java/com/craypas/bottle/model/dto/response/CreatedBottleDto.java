package com.craypas.bottle.model.dto.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.craypas.bottle.model.entity.Bottle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedBottleDto {
	private long id;
	private long writerId;
	private long bottieId;
	private String content;
	private int type;
	private int sentiment;
	private String ttsPath;
	private String regTime;
	private int status;

	// public Bottle toEntity() throws ParseException {
	// 	return Bottle.builder()
	// 		.id(id)
	// 		.writerId(writerId)
	// 		.bottieId(bottieId)
	// 		.content(content)
	// 		.type(type)
	// 		.content(content)
	// 		.sentiment(sentiment)
	// 		.regTime(dateConverter(regTime))
	// 		.status(status).build();
	// }

	public Date dateConverter(String input) throws ParseException {
		SimpleDateFormat foramatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return foramatter.parse(input);
	}
}
