package com.ex.board.service;

import com.ex.board.model.ReplyVO;

import java.util.List;

public interface ReplyService {

    List<ReplyVO> getReplyList(int bid) throws Exception;

    int saveReply(ReplyVO replyVO) throws Exception;

    int updateReply(ReplyVO replyVO) throws Exception;

    int deleteReply(int rid) throws Exception;
}
