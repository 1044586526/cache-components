package com.aspire.nsmp.cache.service;


import com.aspire.nsmp.cache.dto.ComponentReqDto;
import com.aspire.nsmp.cache.dto.ResultDTO;

/**
 * @author ljh
 * @version 1.0
 * @date 2023/9/21 14:31
 */
public interface ComponentCacheService {

    ResultDTO md5Search(ComponentReqDto reqDto);


    ResultDTO gavSearch(ComponentReqDto reqDto);

    ResultDTO fileNameSearch(ComponentReqDto reqDto);
}
