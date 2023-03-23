package com.supos.eco.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<E> {
    private int code=0;
    private String message="success";
    private Pagination pagination;
    private List<E> list;
}

