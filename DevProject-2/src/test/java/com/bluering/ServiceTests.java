package com.bluering;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bluering.domain.BoardDTO;
import com.bluering.mapper.BoardMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

	@Autowired
	private BoardMapper boardMapper;

	
	@Test
	public void registerBoard() {
		BoardDTO params = new BoardDTO();
		params.setTitle("테스트 제목 삽입");
		params.setWriter("테스트 작성자 삽입");
		params.setContent("테스트 글 삽입");
		int queryResult = 0;
		if(params.getIdx() == null) {  //게시글 번호(idx)의 유무에 따라 INSERT / UPDATE로 분기
			queryResult = boardMapper.insertBoard(params);
		}else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		System.out.println(queryResult);
	}

	
}
