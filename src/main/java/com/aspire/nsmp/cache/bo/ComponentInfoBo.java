package com.aspire.nsmp.cache.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/25 15:11
 */
@Data
public class ComponentInfoBo implements Serializable {
    /**
     * 组件ID
     */
    private String compId;

    /**
     * 组件groupId值
     */
    private String group;

    /**
     * 组件artifactId值
     */
    private String name;

    /**
     * 组件版本
     */
    private String version;

    /**
     * 组件发布日期
     */
    private String assetCreated;

    /**
     * 许可证信息
     */
    private String assetLicName;

    /**
     * MD5
     */
    private String assetMd5;

    /**
     * 文件sha1
     */
    private String assetSha1;

    /**
     * 厂商URL
     */
    private String assetUrl;

    /**
     * 组件描述
     */
    private String assetDesc;

    /**
     * 所属语言
     */
    private String language;

    /**
     * 所属组件库
     */
    private String repository;

    /**
     * 开发者
     */
    private String developer;
}
