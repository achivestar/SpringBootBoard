package com.bluering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluering.domain.BoardDTO;
import com.bluering.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/board/write.do")  // 신규등록과 수정페이지를 따로 두어도 되지만  신규등록과 수정 작업은 게시글을 등록하는 행위가 같으므로 하나로 처리하여 코드를 조금 더  보장받기 위함
	public String openBoardWrite(Model model, @RequestParam(value="idx", required=false) Long idx) {
		//새로운 글을 등록할 때는 게시글 번호(idx)가 필요 없으므로  required 속성을 false로 지정
		// 필수(required) 속성은  default 값이 true이며 false로 지정하지 않으면 오류 발생
		
		if(idx==null) {   // 게시글 리스트 페이지에서 게시글 등록 페이지로 이동하면 idx는 null로 전송
			model.addAttribute("board",new BoardDTO());  //비어 있는 객체 전달
		}else {  //게시글 상세 페이지에서 수정하기 버튼을 클릭하면 컨트롤러에 게시글 번호(idx)가 파라미터로 넘어오고
			BoardDTO board = boardService.getBoardDetail(idx);  //게시글 정보를 포함하고 있는 객체 전달
			if(board==null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board",board);  //게시글 정보를 포함하고 있는 객체 전달
		}
		
		return "board/write";
	}
	
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/board/list.do";
	}
	
	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);

		return "board/list";
	}
}
