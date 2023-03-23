package com.supos.eco.vo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author caonuoqi@supos.com
 * @date 2022/7/19
 */
@Data
public class UserSearch implements Serializable {
    private String keyword;
    private int pageIndex;
    private int pageSize;
    private String companyCode;


}
