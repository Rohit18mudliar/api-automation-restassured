package com.api.models.response;

public class Pagination{
	private int total;
	private int limit;
	private int totalPages;
	private int page;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}
}
