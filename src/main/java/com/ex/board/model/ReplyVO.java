package com.ex.board.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReplyVO {

    private int rid;
    private int bid;
    private String content;
    private String writer;
    private Timestamp regDt;
    private Timestamp editDt;

}
