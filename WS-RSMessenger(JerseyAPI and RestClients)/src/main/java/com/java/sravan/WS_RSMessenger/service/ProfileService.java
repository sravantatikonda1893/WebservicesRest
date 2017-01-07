package com.java.sravan.WS_RSMessenger.service;
import java.util.List;
import java.util.Map;
import com.java.sravan.WS_RSMessenger.database.Database;
import com.java.sravan.WS_RSMessenger.model.Profile;

public class ProfileService 
{
	public ProfileService() {
		profiles.put("sravan", new Profile(3, "sravantati", "sravan","tatikonda"));
	}
	private Map<String,Profile> profiles=Database.getProfiles();
	public List<Profile> getProfiles(){
		return (List<Profile>) profiles.values();
	}
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		return profiles.put(profile.getProfileName(), profile);
	}

	public Profile updateProfile(Profile profile)
	{ 	if(profile.getProfileName().isEmpty())
		return null;
	return profiles.put(profile.getProfileName(), profile);
	}
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);	
	}
}