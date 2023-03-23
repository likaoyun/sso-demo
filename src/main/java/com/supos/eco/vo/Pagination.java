package com.supos.eco.vo;

import lombok.Data;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
@Data
public class Pagination {
    public int total;
    public int pageSize;
    public int pageIndex;
}
