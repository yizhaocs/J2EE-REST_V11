package org.koushik.javabrains.messenger11.resources;

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

import org.koushik.javabrains.messenger11.model.Message;
import org.koushik.javabrains.messenger11.model.Profile;
import org.koushik.javabrains.messenger11.service.MessageService;
import org.koushik.javabrains.messenger11.service.ProfileService;


/**
 * REST Web Services 24 - Using Context and BeanParam annotatio
 * http://localhost:8080/messenger11/webapi/profiles
 * http://localhost:8080/messenger11/webapi/profiles/koushik
 * POST http://localhost:8080/messenger11/webapi/profiles {"firstName": "yi","lastName": "zhao","profileName": "admin"}
 */
@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	ProfileService profileService = new ProfileService();
	
	@GET
	// @Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	// @Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("profileName")String profileName){
		return profileService.getProfile(profileName);
	}
	
	@POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Profile addMessage(Profile profile){
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Profile updateMessage(@PathParam("profileName")String profileName, Profile profile){
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	// @Produces(MediaType.APPLICATION_JSON)
	public void deleteProfile(@PathParam("profileName")String profileName){
		 profileService.removeProfile(profileName);
	}
}
