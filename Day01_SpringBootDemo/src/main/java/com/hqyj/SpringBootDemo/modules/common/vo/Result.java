package com.hqyj.SpringBootDemo.modules.common.vo;

public class Result<T> {
	//private final static Integer SUCCESS = 100;
	
	private int status;
	private String message;
	private T object;
	
		
	public Result(int status, String message, T object) {
		this.status = status;
		this.message = message;
		this.object = object;
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}
	public void setObject(T object) {
		this.object = object;
	}

	public enum ResultStatus{
		SUCCESS(200),FAILED(500);
		
		public int status;
		
		private ResultStatus(int status) {
			this.status = status;
		}
	}
	
	/*
	 * 测试
	 * public static void main(String[] args) {
	 * System.out.println(ResultStatus.SUCCESS.status); }
	 */
}
