package controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import exception.ItemNotFoundException;
import bean.ExceptionMessage;
@Provider
public class ItemNotfoundExceptionMapper implements ExceptionMapper<ItemNotFoundException>{
	@Override
	public Response toResponse(ItemNotFoundException cfne){
		ExceptionMessage exceptionMessage= new ExceptionMessage(cfne.getExceptionMessage(),"404");
		return Response.status(Status.NOT_FOUND).entity(exceptionMessage).build();
		}
}
