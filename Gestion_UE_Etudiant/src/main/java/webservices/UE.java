package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UE {
    UniteEnseignementBusiness helper=new UniteEnseignementBusiness();

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_XML)   // The response will be returned as plain text
    public Response CreateEnseignement() {

        UniteEnseignement ue = new UniteEnseignement(123,"Informatique", "Mme Aroua Douiri", 6, 2);

        boolean a=helper.addUniteEnseignement(ue);
        // Build an HTTP 200 (OK) response with the message "Hello World!"
        if(a==true){
            return Response
                .status(200)                  // HTTP status code 200 = success
                .entity("Status 201 Created ")       // The body of the response
                .build();            }
        else{
            return Response
                    .status(401)                  // HTTP status code 200 = success
                    .entity("404 Not Found ")       // The body of the response
                    .build();
        }
    }
    //afficher tt les listes
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUE(){
        return Response
                .status(200)                  // HTTP status code 200 = success
                .entity(this.helper.getListeUE())       // The body of the response
                .build();
    }



}
