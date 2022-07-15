package com.ex.board.controller;

import com.ex.board.mapper.ReplyMapper;
import com.ex.board.model.PageInfo;
import com.ex.board.model.ReplyVO;
import com.ex.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/replyBoard")
public class ReplyBoardController {

    @Autowired
    private ReplyService replyService;

    @GetMapping("/getReplyList")
    public List<ReplyVO> getReplyList(int bid) throws Exception {
        return replyService.getReplyList(bid);
    }

    @PostMapping("/saveReply")
    public Map<String, String> saveReplyVO(@RequestBody ReplyVO replyVO, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();

        try {
            int saveResult = replyService.saveReply(replyVO);
            if (saveResult > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                result.put("Status", "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping("/replyUpdate")
    public Map<String, String> replyUpdate(@RequestBody ReplyVO replyVO, HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();

        try {
            int update = replyService.updateReply(replyVO);
            if (update > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                result.put("Status", "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping("/replyDelete")
    public Map<String, String> replyDelete(@RequestParam int rid, HttpServletResponse response) {

        Map<String, String> result = new HashMap<>();

        try {
            replyService.deleteReply(rid);
            response.setStatus(HttpServletResponse.SC_OK);
            result.put("Status", "OK");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
