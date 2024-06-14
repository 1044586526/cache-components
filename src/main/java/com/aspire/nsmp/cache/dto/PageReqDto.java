package com.aspire.nsmp.cache.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/21 14:18
 */
@Data
public class PageReqDto implements Serializable {

    /**
     * 页码
     */
    private Integer pageIndex;

    /**
     *  分页大小
     */
    private Integer pageSize;


    /**
     * 开始索引
     */
    private Integer startIndex;

    /**
     * 结束索引
     */
    private Integer endIndex;
}
