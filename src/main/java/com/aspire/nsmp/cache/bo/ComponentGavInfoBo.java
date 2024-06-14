package com.aspire.nsmp.cache.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/25 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentGavInfoBo implements Serializable {
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
}
