package com.alan.sample.model;

import java.io.Serializable;
import java.util.List;

public class MenuInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private List<AddressInfo> addressList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AddressInfo> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<AddressInfo> addressList) {
		this.addressList = addressList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfoVO [name=");
		builder.append(name);
		builder.append(", addressList=");
		builder.append(addressList);
		builder.append("]");
		return builder.toString();
	}
}
