package com.alan.sample;

import com.alan.sample.model.IFStateEnum;
import com.alan.sample.model.RetCodeEnum;

public class BaseResponse<T> {

	private IFStateEnum state;
	private RetCodeEnum returnCode;
	private String message;
	private T result;
	
	public BaseResponse(){}
	public BaseResponse(IFStateEnum state , RetCodeEnum returnCode , T result , String message){
		this.state = state;
		this.returnCode = returnCode;
		this.result = result;
		this.message = message;
	}

	public IFStateEnum getState() {
		return state;
	}

	public void setState(IFStateEnum state) {
		this.state = state;
	}

	public RetCodeEnum getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(RetCodeEnum returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
