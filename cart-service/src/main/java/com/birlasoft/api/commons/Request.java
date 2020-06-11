package com.birlasoft.api.commons;

public class Request {
	
	private int productId;

	public Request(int productId) {
		super();
		this.productId = productId;
	}

	public Request() {
		super();
	}

	@Override
	public String toString() {
		return "Request [productId=" + productId + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (productId != other.productId)
			return false;
		return true;
	}
	
	

}
