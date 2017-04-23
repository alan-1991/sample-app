package com.alan.sample.model;

public enum RetCodeEnum {

	SUCCESS("S" , "成功")
	,FAIL("F" , "失败")
	,UNAUTH("UNAUTH" , "没有权限")
	,FORBIDEN("FORBIDEN" , "禁止")
	;
	RetCodeEnum(String code , String text){
		this.code = code;
		this.text = text;
	}
	private String code;
	private String text;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
