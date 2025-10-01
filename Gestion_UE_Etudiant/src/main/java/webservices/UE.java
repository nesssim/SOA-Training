package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UE {
    UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)   // ✅ Changed to JSON
    public Response CreateEnseignement() {
        UniteEnseignement ue = new UniteEnseignement(333, "Informatique", "MR Nessim Mezhoud", 6, 2);
        boolean a = helper.addUniteEnseignement(ue);

        if (a == true) {
            return Response
                    .status(201)                  // ✅ 201 for Created
                    .entity("Status 201Created")
                    .build();
        } else {
            return Response
                    .status(400)                  // ✅ 400 for Bad Request
                    .entity(" 404 Not Found }")
                    .build();
        }
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUE() {
        return Response
                .status(200)
                .entity(this.helper.getListeUE())
                .build();
    }

    @Path("/findSem")  // ✅ Fixed path
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemester(@QueryParam("semester") int semester) {
        return Response
                .status(200)
                .entity(this.helper.getUEBySemestre(semester))
                .build();
    }

    @Path("/delUe")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUE(@QueryParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response
                    .status(200)
                    .entity("Status 200 OK - Deleted")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("Status 404 Not Found")
                    .build();
        }
    }

    @PUT
    @Path("/updateUe")  // This captures the code from URL path
    @Consumes(MediaType.APPLICATION_XML)  // Accept XML input
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateUE(@QueryParam("code") int code, UniteEnseignement ue) {

        if (helper.updateUniteEnseignement(code, ue)) {
            return Response
                    .status(200)
                    .entity("Status 200 OK - Updated")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("Status 404 Not Found")
                    .build();
        }
    }

    @Path("/findUeCode")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@QueryParam("code") int code) {
        UniteEnseignement ue = this.helper.getUEByCode(code);
        if (ue != null) {
            return Response
                    .status(200)
                    .entity(ue).build();

        } else {
            return Response
                    .status(404)
                    .entity("Status 404 Not Found")
                    .build();
        }
    }


}