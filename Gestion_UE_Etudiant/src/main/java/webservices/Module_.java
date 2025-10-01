package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/module")
public class Module_ {

    ModuleBusiness helper = new ModuleBusiness();
    UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateModule() {
        Module module = new Module("M103", "Réseaux", 3, 25, Module.TypeModule.PROFESSIONNEL, ueHelper.getUEByCode(1));
        boolean a = helper.addModule(module);

        if (a) {
            // Fetch the complete module with its UniteEnseignement
            Module createdModule = helper.getModuleByMatricule(module.getMatricule());
            return Response
                    .status(201)
                    .entity(createdModule)  // Return the module object directly
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity("400 Bad Request")
                    .build();
        }
    }



    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListeModules() {
        return Response
                .status(200)
                .entity(this.helper.getAllModules())
                .build();
    }

    @Path("/ModuleMat")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByCode(@QueryParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if (module != null) {
            return Response
                    .status(200)
                    .entity(module)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("404 Not Found")
                    .build();
        }
    }



    @Path("/delModule")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteModule(@QueryParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response
                    .status(200)
                    .entity("200 OK")
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("404 Not Found")
                    .build();

        }
    }

    @Path("/updateModule")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateModule(@QueryParam("matricule") String matricule, Module module) {

        if (helper.updateModule(matricule, module)) {
            return Response
                    .status(200)
                    .entity(module)
                    .build();
        } else {
            return Response
                    .status(404)
                    .entity("404 Not Found")
                    .build();
        }
    }
    @Path("/UEModules")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUECode(@QueryParam("IdUE")int ue) {

        List<Module> modules = helper.getModulesByUE(ueHelper.getUEByCode(ue));
        return Response
                .status(200)
                .entity(modules)
                .build();

    }
}