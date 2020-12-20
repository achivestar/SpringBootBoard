package com.bluering.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluering.domain.BoardDTO;
import com.bluering.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		if(params.getIdx() == null) {  //게시글 번호(idx)의 유무에 따라 INSERT / UPDATE로 분기
			queryResult = boardMapper.insertBoard(params);
		}else {
			queryResult = boardMapper.updateBoard(params);
		}
		
		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
			int queryResult = 0;
			BoardDTO board = boardMapper.selectBoardDetail(idx);
			if(board!=null && "N".equals(board.getDeleteYn())) {  // 사용중인 상태의 게시글인 경우에만 게시글을 삭제
				queryResult = boardMapper.deleteBoard(idx);
			}
			return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList(); //NPE 방지를 위해 비어있는 리스트 선언
		
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
}
