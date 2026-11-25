package com.api.models.response;

import java.util.List;

public class BookingListResponse{
	private Pagination pagination;
	private List<DataItem> data;
	private boolean success;

	public BookingListResponse(Pagination pagination, List<DataItem> data, boolean success) {
		this.pagination = pagination;
		this.data = data;
		this.success = success;
	}

	public BookingListResponse() {
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}
}