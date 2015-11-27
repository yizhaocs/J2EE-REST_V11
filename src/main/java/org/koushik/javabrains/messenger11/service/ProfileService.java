package org.koushik.javabrains.messenger11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger11.database.DatabaseClass;
import org.koushik.javabrains.messenger11.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService(){
		profiles.put("koushik", new Profile(1L, "koushik", "koushik", "Kothagal"));
	}

	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}

	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile){
		if(profile.getId() <= 0){
			return null;
		}

		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
