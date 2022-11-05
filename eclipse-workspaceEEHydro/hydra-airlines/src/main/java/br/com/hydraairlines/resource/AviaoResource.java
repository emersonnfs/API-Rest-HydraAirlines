package br.com.hydraairlines.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.hydraairlines.bo.AviaoBO;
import br.com.hydraairlines.to.AviaoTO;

@Path("/aviao")
public class AviaoResource {
	AviaoBO cb = new AviaoBO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AviaoTO> buscar() throws SQLException {
		return cb.listar();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AviaoTO buscar(@PathParam("id") int id) throws SQLException {
		return cb.listar(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrar(AviaoTO a) throws SQLException{
		return Response.status( 200 ).entity(cb.cadastrar(a)).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizar(AviaoTO a, @PathParam("id") int id) throws SQLException{
		a.setId(id);
		return Response.ok().status(200).entity(cb.atualiza(a)).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") int id) throws SQLException{
		return Response.status(200).entity(cb.remover(id)).build();
	}
}
