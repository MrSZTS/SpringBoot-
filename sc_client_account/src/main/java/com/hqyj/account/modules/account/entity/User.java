package com.hqyj.account.modules.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户类
 * 
 * @author: HymanHu
 * @date: 2019年11月28日
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//@Id	@GeneratedValue 为一个实体生成一个唯一标识的主键
	private int userId;
	private String userName;
	private String password;
	private String userImg;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;
	
	private String accountName;
	//@Transient	就是在给某个javabean上需要添加个属性，但是这个属性你又不希望给存到数据库中去，仅仅是做个临时变量，用一下。
	private boolean rememberMe;
	
	List<City> cities;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	
	

}
