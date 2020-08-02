package com.agileengine.moneyaccountingsystem.response;

public class GenericResponse<Response> {
	
	private Status status;
	private Response response;
	
	public GenericResponse(){}
	
	public GenericResponse(Response response, StatusEnum status){
		this.setResponse(response);
		this.setStatus(new Status(status.getId(), status.getMessage()));
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Response getResponse() {
		return response;
	}
	
	public void setResponse(Response t) {
		this.response = t;
	}
	
	 

}
