package com.aspire.nsmp.cache.exception;

import com.aspire.nsmp.cache.constants.ResultCode;
import lombok.Data;

/**
 * 自定义异常
 *
 * @author
 */
@Data
public class BizException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;

    public BizException(String msg) {
		super(msg);
		this.code = ResultCode.FAIL.getCode();
		this.msg = msg;
	}

	public BizException(ResultCode resultCode) {
		super(resultCode.getMsg());
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
	}

	public BizException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public BizException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BizException(Integer code, String msg) {
		super(msg);
		this.code = String.valueOf(code);
		this.msg = msg;
	}

	public BizException(String code, String msg, Throwable e) {
		super(msg, e);
		this.code = code;
		this.msg = msg;
	}


}
