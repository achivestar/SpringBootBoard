package com.bluering.service;

import java.util.List;

import com.bluering.domain.BoardDTO;
import com.bluering.paging.Criteria;

public interface BoardService {

	public boolean registerBoard(BoardDTO params);
	
	public BoardDTO getBoardDetail(Long idx);
	
	public boolean deleteBoard(Long idx);
	
	public List<BoardDTO> getBoardList(BoardDTO params);
}
