package com.testedge.ws.rest.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.testedge.ws.rest.model.Comment;
import com.testedge.ws.rest.webi.EdgeServices;

@Path("/testEdge")
public class ServiceLoginUR {
	
/**
 * Si se envia mail vacio, dara error de 500
 * @param comment
 * @return comentario modificado
 */
	@POST
	@Path("/actualizarComments")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response validarUsuario(Comment comment) {
		EdgeServices servicios = new EdgeServices();
		Comment comentario = servicios.actualizarComentarios(comment);
	
		if("".equals(comentario.getEmail())) {
			return Response.status(500)
				      .type(MediaType.APPLICATION_JSON)
				      .entity(comentario)
				      .build();
		}else {
			return Response.status(200)
				      .type(MediaType.APPLICATION_JSON)
				      .entity(comentario)
				      .build();
		}
		
	}
	
	/**
	 * 
	 * @return los comentarios en formato json
	 */
	  @GET
	  @Path("/verComments")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response sayPlainTextHello() {
		EdgeServices servicios = new EdgeServices();			  
		  return servicios.verComentarios();			 
	  
	  }
	  

}
