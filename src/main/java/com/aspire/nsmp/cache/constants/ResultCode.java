package com.aspire.nsmp.cache.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author ljh
 * @date 2021年4月29日
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResultCode implements Serializable {

    SUCCESS("00000", "请求成功"),
    FAIL("99999", "系统出错"),
    PARA("11111", "参数校验失败"),

    USER_ERROR("A0001", "用户端错误"),
    USER_LOGIN_ERROR("A0200", "用户登录异常"),
    USER_NOT_EXIST("A0201", "用户不存在."),
    USER_EXIST("A0250", "手机号码{%s}已在平台注册，可直接登录或找回密码."),
    USER_EXIST_UPDATE("A0251", "手机号码{%s}已在平台注册，更新用户名失败."),
    USER_ACCOUNT_LOCKED("A0202", "用户账户被冻结"),
    USER_ACCOUNT_INVALID("A0203", "账号已注销,无法登录"),
    USER_AUTHORIZED_ERROR("A0204", "用户认证失败"),
    USER_TOKEN_NOT_EXIST("A0205", "用户未登录"),
    USER_ACCOUNT_EXCEPTION("A0206", "用户信息异常"),
    USER_ROLE_EXCEPTION("A0207", "请给用户关联角色"),
    USER_CH_NAME_NULL("A0208", "用户中文名称为空或非法"),
    USER_NULL("A0209", "用户信息不可为空"),
    USER_PASSWORD_NULL("A0209", "用户密码不可为空"),
    USER_PASSWORD_DECODE_FAIL("A0209", "用户密码解码出错"),


    USERNAME_OR_PASSWORD_ERROR("A0210", "用户名或密码错误"),
    USERNAME_PASSWORD_NOT_STRONG("A0213", "用户密码强度太弱或长度不满足"),
    USERNAME_PASSWORD_KEYWORDS("A0214", "用户密码包含关键字"),
    USERNAME_PASSWORD_PINYIN("A0215", "用户密码包含拼音或拼音缩写"),
    USERNAME_PASSWORD_WEAK("A0216", "用户密码包含弱口令字典"),
    USERNAME_PASSWORD_DECRYPT("A0217", "解密出错,请联系技术中心"),
    INPUT_PASSWORD_EXCEED_LIMIT("A0211", "账号已锁定,请5分钟后再试!"),
    CLIENT_AUTHENTICATION_FAILED("A0212", "客户端认证失败"),
    TOKEN_INVALID_OR_EXPIRED("A0230", "用户信息无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN("A0231", "token已被禁止访问"),

    AUTHORIZED_ERROR("A0300", "访问权限异常"),
    ACCESS_UNAUTHORIZED("A0301", "用户无权限访问"),
    ACCESS_UNAUTHORIZED_DATA("A0303", "用户无权限操作此数据"),
    FORBIDDEN_OPERATION("A0302", "演示环境禁止修改、删除重要数据，请本地部署后测试"),


    PARAM_ERROR("A0400", "用户请求参数错误"),
    PARAM_IS_NULL("A0410", "请求必填参数为空"),
    QUERY_MODE_IS_NULL("A0411", "查询模式为空"),

    USER_UPLOAD_FILE_ERROR("A0700", "用户上传文件异常"),
    USER_UPLOAD_FILE_EMPTY("A0704", "上传文件不能为空"),
    USER_UPLOAD_FILE_TYPE_NOT_MATCH("A0701", "用户上传文件类型不匹配"),
    USER_UPLOAD_FILE_SIZE_EXCEEDS("A0702", "用户上传文件太大"),
    USER_UPLOAD_IMAGE_SIZE_EXCEEDS("A0703", "用户上传图片太大"),
    EXCEL_HEAD_ERROR("A0705", "导入的模板有误!"),
    EXCEL_DATA_ERROR("A0706", "导入的数据有误!"),
    FILE_COPY_ERROR("A0707", "文件复制出错"),
    FILE_ZIP_ERROR("A0708", "文件压缩出错"),

    SYSTEM_DISASTER_RECOVERY_TRIGGER("B0200", "系统容灾功能被出发"),
    FLOW_LIMITING("B0210", "系统限流"),
    SYSTEM_FUNCTION_DEGRADATION("B0220", "系统功能降级"),

    SYSTEM_RESOURCE_ERROR("B0300", "系统资源异常"),
    SYSTEM_RESOURCE_EXHAUSTION("B0310", "系统资源耗尽"),
    SYSTEM_RESOURCE_ACCESS_ERROR("B0320", "系统资源访问异常"),
    SYSTEM_READ_DISK_FILE_ERROR("B0321", "系统读取磁盘文件失败"),

    SEND_MSG_BIZ_TYPE_ERROR("B0501", "发送短信的业务场景不存在"),
    PHONE_NUM_ILLEGAL("B0502", "手机号码格式不正确"),
    PHONE_MSG_CODE_ERROR("B0503", "短信验证码错误"),
    CAPTCHA_CODE_ERROR("B0503", "图形验证码不正确"),

    CALL_THIRD_PARTY_SERVICE_ERROR("C0001", "调用第三方服务出错"),
    MIDDLEWARE_SERVICE_ERROR("C0100", "中间件服务出错"),
    INTERFACE_NOT_EXIST("C0113", "接口不存在"),

    MESSAGE_SERVICE_ERROR("C0120", "消息服务出错"),
    MESSAGE_DELIVERY_ERROR("C0121", "消息投递出错"),
    MESSAGE_CONSUMPTION_ERROR("C0122", "消息消费出错"),
    MESSAGE_SUBSCRIPTION_ERROR("C0123", "消息订阅出错"),
    MESSAGE_GROUP_NOT_FOUND("C0124", "消息分组未查到"),

    DATABASE_ERROR("C0300", "数据库服务出错"),
    DATABASE_TABLE_NOT_EXIST("C0311", "表不存在"),
    DATABASE_DATA_NOT_EXIST("C0313", "编辑的数据不存在"),
    WORK_ORDER_TIME_OUT("C0314", "工单时间已结束，不能提交反馈"),

    CLOUD_NOT_SUBMIT_FEEDBACK("C0314", "已提交的工单反馈正在上报，请等待系统上报完成后再提交"),
    STATE_ERROR_FEEDBACK("C0317", "当前工单未接收，请等待系统下发后再提交"),

    DATABASE_COLUMN_NOT_EXIST("C0312", "列不存在"),
    DATABASE_DUPLICATE_COLUMN_NAME("C0321", "多表关联中存在多个相同名称的列"),
    DATABASE_DEADLOCK("C0331", "数据库死锁"),
    DATABASE_PRIMARY_KEY_CONFLICT("C0341", "主键冲突"),

    CMD_NOT_SUPPORT_RESEND("C0400", "下发省平台状态为：未下发、下发失败才支持重发"),
    CMD_NOT_SUPPORT_READED("C0401", "只有待查阅状态的工单，才需要查阅确认"),
    CMD_FILE_IS_EMPTY("C0402", "上传文件内容为空"),
    CMD_CREATE_FILE_ERROR("C0403", "操作异常,创建反馈zip文件失败。"),
    CMD_FEEDBACK_COMPLETE("C0404", "该笔工单指令已反馈,不能重复反馈。"),
    CMD_REC_NOT_EXIST("C0405", "上报附件所属指令记录不存在,请刷新列表重试"),
    FILE_TYPE_NOT_SUPPORT("C0406", "文件类型不支持"),
    FILE_NOT_EXIST("C0407", "下载失败,该文件不存在"),
    FILE_EXCEEDED_FILE_SIZE("C0408", "上传失败，超出单个文件最大大小%s限制"),
    FILE_SAVE_ERROR("C0409", "上传失败，保存文件异常"),
    ROLE_BELONG_PROVINCE_CODE_IS_NULL("C0410", "角色所属省份编码为空"),
    ROLE_UNAUTHORIZED_OPT_RESEND("C0411", "该账号无权限操作指令重发功能"),

    INTERNET_ASSET_REPORT_SUCCESS("D0001","暴露面资产导入成功"),
    INTERNET_ASSET_REPORT_FAIL("D0002","暴露面资产导入失败"),
    INTERNET_ASSET_REPORT_DATA_ILLEGAL("D0003","暴露面资产数据不合法"),

    INTERNET_CDN_ASSET_REPORT_FAIL("D0010","CDN暴露面资产导入失败"),

    AUDIT_TASK_CREATE_SUCCESS("E0001","稽核任务创建成功！"),
    AUDIT_TASK_CREATE_FAIL("E0002","稽核任务创建失败！"),
    SCAN_ASSET_IMPORT_FAIL("E0003","扫描资产导入失败！"),
    SCAN_ASSET_IMPORT_SUCCESS("E0005","扫描资产导入成功！"),
    AUDIT_TASK_EXECUTE_FAIL("E0006","稽核任务执行失败！"),
    AUDIT_TASK_EXECUTE_SUCCESS("E0007","稽核任务执行成功！"),

    INTERNET_ASSET_IP_SEGMENT_IMPORT_SUCCESS("F0001","IP网段导入成功！"),
    INTERNET_ASSET_IP_SEGMENT_IMPORT_DATA_FAIL("F0002","IP网段导入失败！"),
    INTERNET_ASSET_IP_SEGMENT_IMPORT_FAIL("F0003","IP网段导入失败！"),

    TASK_REC_NOT_EXIST_FAIL("G0001","该异常分析任务不存在."),
    TASK_CREATE_NON_STORAGE_FAIL("G0002","创建任务失败,当前异常分析任务状态非'暂存'状态.不允许创建."),
    TASK_STORAGE_FAIL("G0003","暂存失败,当前异常分析任务状态非'暂存'状态,不允许再次'暂存'."),
    TASK_CREATE_XXL_JOB_FAIL("G0004","保存xxlJob任务失败"),
    TASK_DEL_LIST_EMPTY_FAIL("G0005","删除失败,要删除的任务不存在"),
    TASK_NO_ALLOW_DEL_FAIL("G0006","删除失败,'暂存'、'未执行'状态的任务才可删除"),
    TASK_EXEC_ING_BAN_EDIT("G0007","当前任务正在执行,请在任务执行完毕后修改"),
    EXPORT_ANAL_ASSET_DATA_FAIL("G0008","导出异常资产分析数据失败."),
    TASK_EXEC_NOT_SINGLE_PERIOD_FAIL("G0009","该任务执行周期非'单次',不支持手动执行任务"),
    TASK_EXEC_REST_CALL_FAIL("G0010","手动执行定时任务失败"),
    TASK_CREATE_FAIL("G0011","创建任务失败"),

    TASK_EXEC_REST_STATUS_ERROR("G0012","当前任务状态不支持手动执行"),
    GET_LAST_MONTH_FAIL("G00012","获取asset最新月份失败"),
    EXEC_SQL_FAIL("G00013","taskId={},ruleId={},sql执行异常，where={}");

    private String code;

    private String msg;

    /**
     * 格式化消息
     *
     * @param param
     * @return
     */
    public String formatMsg(Object... param) {
        return String.format(this.getMsg(), param);
    }

}
