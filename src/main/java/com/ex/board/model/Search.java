package com.ex.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Search extends PageInfo {

    private String searchType;
    private String keyword;

}

