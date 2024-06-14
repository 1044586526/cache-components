package com.aspire.nsmp.cache.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;


/**
 *
 * @Description 组件公共枚举类
 * @Author: ljh
 * @Date: 2023/9/21 14:54
 */

public class ComponentConstants {

    public interface CacheConstants{
        String MD5 = "md5";

        String GAV = "gav";

        String FILE_NAME = "fileName";

        Integer THREE = 3;

    }


    /**
     * 缓存类型
     */
    @Getter
    @AllArgsConstructor
    public enum CacheType {

        MD5("md5","MD5"),
        GAV("gav","GAV"),
        FILE_NAME("fileName","FILE_NAME");


        private String code;

        private String desc;

        public static CacheType enumByCode(String code){
            return Stream.of(CacheType.values()).filter(tt ->tt.getCode().equals(code)).findFirst().orElse(null);
        }

        public static CacheType enumByDesc(String desc){
            return Stream.of(CacheType.values()).filter(tt ->tt.getDesc().equals(desc)).findFirst().orElse(null);
        }
    }

}
