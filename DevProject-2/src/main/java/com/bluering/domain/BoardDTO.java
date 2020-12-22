package com.bluering.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO extends CommonDTO{

	private Long idx;
	
	private String title;
	
	private String content;
	
	private String writer;
	
	private int viewCnt;
	
	private String noticeYn;
	
	private String secretYn;

}
