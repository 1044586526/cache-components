package com.aspire.nsmp.cache.dto;


import com.aspire.nsmp.cache.constants.ResultCode;
import com.aspire.nsmp.cache.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 统一出口封装类.
 * requestId: 独立于单次请求, 有利于日志追踪.
 * @author ljh
 * @date 2023年4月20日
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO<T> extends PageResDto {

	private static final long serialVersionUID = 1L;

	private String resultCode;
    private String resultMsg;
    private String requestId;
    private String errorDataKey;
    private T data;


	private ResultDTO(ResultCode result) {
		this.resultCode = result.getCode();
		this.resultMsg = result.getMsg();
	}


	public static ResultDTO fail(String msg) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(ResultCode.FAIL.getCode());
		resultDTO.setResultMsg(msg);
		return resultDTO;
	}

	public static ResultDTO fail(ResultCode result, Object o) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(result.getCode());
		resultDTO.setResultMsg(result.getMsg());
		resultDTO.setData(o);
		return resultDTO;
	}

	public static ResultDTO fail(ResultCode result) {
		return new ResultDTO(result);
	}

	public static ResultDTO fail() {
		return new ResultDTO(ResultCode.FAIL);
	}

	public static <T> ResultDTO<T> success(T data) {
		ResultDTO resultDTO = new ResultDTO(ResultCode.SUCCESS);
		resultDTO.setData(data);
		return resultDTO;
	}

	public static ResultDTO successMsg(String resultMsg) {
		ResultDTO resultDTO = new ResultDTO(ResultCode.SUCCESS);
		resultDTO.setResultMsg(resultMsg);
		return resultDTO;
	}

	public static ResultDTO success() {
		return new ResultDTO(ResultCode.SUCCESS);
	}

	public boolean isSuccess() {
		if (Objects.equals(ResultCode.SUCCESS.getCode(), this.getResultCode()))
			return true;
		return false;
	}

	public static ResultDTO success(ResultCode resultCode) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(resultCode.getCode());
		resultDTO.setResultMsg(resultCode.getMsg());
		return resultDTO;
	}

	public static ResultDTO success(String resultCode, String resultMsg) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(resultCode);
		resultDTO.setResultMsg(resultMsg);
		return resultDTO;
	}

	public static <T> ResultDTO<T> success(String resultCode, String resultMsg, T data) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(resultCode);
		resultDTO.setResultMsg(resultMsg);
		resultDTO.setData(data);
		return resultDTO;
	}

	public static ResultDTO fail(BizException e) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(e.getCode() + "");
		String msg = e.getMsg();
		resultDTO.setResultMsg(msg);
		log.error("===>: fail", e);
		return resultDTO;
	}

	public static ResultDTO fail(int i, String resultMsg) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(i+"");
		resultDTO.setResultMsg(resultMsg);
		return resultDTO;
	}

	public static ResultDTO fail(String resultCode, String resultMsg) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(resultCode);
		resultDTO.setResultMsg(resultMsg);
		return resultDTO;
	}

	public static ResultDTO fail(String resultCode, String resultMsg, String errorDataKey) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResultCode(resultCode);
		resultDTO.setResultMsg(resultMsg);
		resultDTO.setErrorDataKey(errorDataKey);
		return resultDTO;
	}
}
