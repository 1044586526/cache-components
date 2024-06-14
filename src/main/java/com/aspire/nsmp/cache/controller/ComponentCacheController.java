package com.aspire.nsmp.cache.controller;

import com.aspire.nsmp.cache.dto.ComponentReqDto;
import com.aspire.nsmp.cache.dto.ResultDTO;
import com.aspire.nsmp.cache.service.ComponentCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description 组件缓存查询服务
 * @Author: ljh
 * @Date: 2023/9/21 14:04
 */

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class ComponentCacheController {

    private final ComponentCacheService cacheService;

    /**
     * md5查询
     */
    @PostMapping(value = "/md5/search" )
    public ResultDTO md5Search(@RequestBody ComponentReqDto reqDto) {
        return cacheService.md5Search(reqDto);
    }


    /**
     * gav查询
     */
    @PostMapping(value = "/gav/search" )
    public ResultDTO gavSearch(@RequestBody ComponentReqDto reqDto) {
        return cacheService.gavSearch(reqDto);
    }

    /**
     * 文件名查询
     */
    @PostMapping(value = "/name/search" )
    public ResultDTO fileNameSearch(@RequestBody ComponentReqDto reqDto) {
        return cacheService.fileNameSearch(reqDto);
    }
}
