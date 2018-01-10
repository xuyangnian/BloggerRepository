package com.fnd.blogger.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@ApiModel(
        value = "CommonResponse",
        description = "前后台交互返回对象结构"
)
public class CommonResponse<T> {
    @ApiModelProperty("具体内容，不同接口返回对象类型不同，success为true时使用")
    private T data;
    @ApiModelProperty(
            value = "交互是否成功",
            example = "true"
    )
    private boolean success;
    @ApiModelProperty(
            value = "处理结果代码",
            example = "000000"
    )
    private String code;
    @ApiModelProperty(
            value = "处理结果描述",
            example = "处理成功"
    )
    private String message;

    public CommonResponse() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> CommonResponse<T> buildRespose4Success(T resp, String msg) {
        CommonResponse<T> commonResponse = new CommonResponse();
        commonResponse.setData(resp);
        commonResponse.setCode("000000");
        commonResponse.setMessage(msg);
        commonResponse.setSuccess(true);
        return commonResponse;
    }

    public static <T> CommonResponse<T> buildRespose4Fail(T resp, String msg) {
        CommonResponse<T> commonResponse = new CommonResponse();
        commonResponse.setData(resp);
        commonResponse.setCode("111111");
        commonResponse.setMessage(msg);
        commonResponse.setSuccess(false);
        return commonResponse;
    }

    public ResponseEntity<CommonResponse<T>> toResponseEntity() {
        return new ResponseEntity(this, HttpStatus.OK);
    }

    public ResponseEntity<CommonResponse<T>> toResponseEntity4RestApiError(HttpStatus status, String errorMsgKey) {
        MultiValueMap httpHeaders = new HttpHeaders();
        String key = StringUtils.isEmpty(errorMsgKey) ? "errorMsg" : errorMsgKey.trim();
        httpHeaders.add(key, this.getCode() + this.getMessage());
        return new ResponseEntity(this, httpHeaders, status);
    }
}
