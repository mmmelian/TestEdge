package com.testedge.ws.rest.webi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.testedge.ws.rest.model.Comment;

public class EdgeServices {
	/**
	 * 
	 * @param comment entidad de la clase comentario
	 * @return el mismo tipo de clase pero modificado con los parametros de entrada.
	 */
	public Comment actualizarComentarios(Comment comment) {
			
			String urlRestService="https://jsonplaceholder.typicode.com/posts/1/comments";		
			Comment commentLocal = null;
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(urlRestService);
			ClientResponse response =  webResource.type("application/json").post(ClientResponse.class,comment);
			commentLocal = response.getEntity(Comment.class);
			
			commentLocal.setEmail(comment.getEmail());
		
			return commentLocal;
		}
	
	public Response verComentarios(){
		  HttpResponse<String> response = null;

		try {
		    HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder()
				     .uri(new URI("https://jsonplaceholder.typicode.com/posts/1/comments"))
				     .GET()
				     .timeout(Duration.ofSeconds(10))
				     .build();
			
			response = client.send(request, BodyHandlers.ofString());
			
			
			return Response.status(response.statusCode())
		      .type(MediaType.APPLICATION_JSON)
		      .entity(response.body())
		      .build();
		} catch (Exception e) {
			e.printStackTrace();
			 return Response.status(response.statusCode())
				      .type(MediaType.APPLICATION_JSON)
				      .entity("Error en la petición")
				      .build();
		
		}
	}
}
