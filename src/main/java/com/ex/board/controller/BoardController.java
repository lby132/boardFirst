package com.ex.board.controller;

import com.ex.board.mapper.ReplyMapper;
import com.ex.board.model.*;
import com.ex.board.service.BoardService;
import com.ex.login.mapper.UserMapper;
import com.ex.login.model.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getBoardList")
    public String getBoard(BoardDTO boardDTO, Model model, UserVO userVO
            , @RequestParam(required = false, defaultValue = "1") int page
            , @RequestParam(required = false, defaultValue = "1") int range
            , @RequestParam(required = false, defaultValue = "title") String searchType
            , @RequestParam(required = false) String keyword
            , HttpServletRequest request) throws Exception {


/*        HttpSession session = request.getSession(false);
        if (session != null) {
            log.info("로그인한 사용자");
            UserVO userInfo = userMapper.getUserInfo(userVO.getUserId());
            log.info("logYn = {}",  userInfo.getLoginYn());
            model.addAttribute("loginYn", userInfo.getLoginYn());
        }

        log.info("로그인 안한 사용자");*/

        Search search = new Search();
        log.info("keyword={}", keyword);
        search.setSearchType(searchType);
        search.setKeyword(keyword);

        log.info("page={}", page);

        int listCnt = boardService.getBoardListCnt(search);

        search.pageInfo(page, range, listCnt);

        model.addAttribute("search", searchType);
        model.addAttribute("pageInfo", search);
        model.addAttribute("boardList", boardService.selectBoard(search));
        return "board/boardList";
    }

    @GetMapping("/getBoardDetail/{idx}")
    public String getBoardDetail(@PathVariable int idx, Model model) throws Exception {
        model.addAttribute("boardDetail", boardService.detailSelectBoard(idx));
        model.addAttribute("rid", replyMapper.getReplyList(idx));
        model.addAttribute("replyVO", new ReplyVO());
        return "board/boardDetail";
    }

    @GetMapping("/getBoardForm")
    public String writeBoard(@RequestParam(required = false) Integer idx, @RequestParam(required = false) String gubun, BoardDTO boardDTO, Model model) throws Exception {
        if (idx != null) {
            model.addAttribute("boardDTO", boardService.detailSelectBoard(idx));
            return "board/updateForm";
        } else if (gubun != null) {
            model.addAttribute("userVO", userMapper.getUserName(gubun));
        }
        return "board/boardForm";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(BoardDTO boardDTO, Model model) throws Exception {
        model.addAttribute("boardDTO", boardService.updateBoard(boardDTO));
        return "redirect:/board/getBoardList";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(BoardDTO boardDTO, Model model) throws Exception {
        model.addAttribute("boardDTO", boardService.insertBoard(boardDTO));
        return "redirect:/board/getBoardList";
    }

    @GetMapping("/deleteBoard/{idx}")
    public String deleteBoard(@PathVariable int idx) throws Exception {
        log.info("djsdkalfjadkl={}", idx);
        boardService.deleteBoard(idx);
        return "redirect:/board/getBoardList";
    }
}
