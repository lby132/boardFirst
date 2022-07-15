package com.ex.board.service;

import com.ex.board.mapper.ReplyMapper;
import com.ex.board.model.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReplyImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<ReplyVO> getReplyList(int bid) throws Exception {
        return replyMapper.getReplyList(bid);
    }

    @Override
    public int saveReply(ReplyVO replyVO) throws Exception {
        return replyMapper.saveReply(replyVO);
    }

    @Override
    public int updateReply(ReplyVO replyVO) throws Exception {
        return replyMapper.updateReply(replyVO);
    }

    @Override
    public int deleteReply(int rid) throws Exception {
        return replyMapper.deleteReply(rid);
    }
}
