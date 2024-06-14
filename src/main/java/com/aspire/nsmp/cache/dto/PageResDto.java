package com.aspire.nsmp.cache.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/21 14:18
 */
@Data
public class PageResDto implements Serializable {

    /**
     *  分页大小
     */
    private Integer pageSize;


    /**
     * 翻页总页数
     */
    private Integer pageCount;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 总数据
     */
    private Integer total;
}
