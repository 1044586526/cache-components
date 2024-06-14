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
public class ComponentFileNameInfoBo implements Serializable {
    /**
     * 组件ID
     */
    private String compId;

    /**
     * 组件版本
     */
    private String fileName;
}
