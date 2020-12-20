package com.bluering;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.bluering.domain.BoardDTO;
import com.bluering.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목");
		params.setContent("1번 게시글 내용");
		params.setWriter("테스터");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result +" 입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long) 1);
		try {
			// Jackson 라이브러리를 이용해서  JSON 문자열로 변경한 뒤 콘솔로 출력
			String boardJson = new ObjectMapper().writeValueAsString(board); 
			
			
			System.out.println("=============================");
			System.out.println(boardJson);
			System.out.println("=============================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 게시글 제목을 수정합니다.");
		params.setContent("1번 게시글 내용을 수정합니다");
		params.setWriter("홍길동으로 수정");
		params.setIdx((long)1);
		int result = boardMapper.updateBoard(params);
		if(result==1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("=============================");
				System.out.println(boardJson);
				System.out.println("=============================");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void testDelete() {
		int result = boardMapper.deleteBoard((long)2);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("=============================");
				System.out.println(boardJson);
				System.out.println("=============================");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if(CollectionUtils.isEmpty(boardList) == false) {
				for(BoardDTO board : boardList) {
					System.out.println("============================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("============================");
				}
			}
		}
	}
}
