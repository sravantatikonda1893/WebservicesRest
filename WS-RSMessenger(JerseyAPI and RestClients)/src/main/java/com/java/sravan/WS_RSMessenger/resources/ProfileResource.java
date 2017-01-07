package com.java.sravan.WS_RSMessenger.resources;

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

import com.java.sravan.WS_RSMessenger.model.Profile;
import com.java.sravan.WS_RSMessenger.service.ProfileService;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/profile")
public class ProfileResource {
	ProfileService ps=new ProfileService();
	@GET
	public List<Profile> getProfiles(){
		return ps.getProfiles();
	}

	@GET
	@Path("{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return ps.getProfile(profileName);
	}
	@POST
//	this method returns the profile object added
	public Profile addProfile(Profile profile){
		return ps.addProfile(profile);
	}
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile){
		profile.setProfileName(profileName);
		return ps.updateProfile(profile);
	}
	@DELETE
	@Path("{profileName}")
	public Profile deleteProfile(@PathParam("profileName") String profileName){
		return ps.removeProfile(profileName);
	}
}
