package com.aspire.nsmp.cache.dto;

import lombok.Data;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/21 14:21
 */
@Data
public class ComponentReqDto extends PageReqDto{

    /**
     * 组件MD5值
     */
    private String md5;

    /**
     * 组件-groupId
     */
    private String groupId;

    /**
     * 组件-artifactId
     */
    private String name;

    /**
     * 组件版本号
     */
    private String version;

    /**
     * 文件名
     */
    private String fileName;


}
