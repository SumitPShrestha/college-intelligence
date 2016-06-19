package com.cms.utility;

import com.cms.dto.UserDTO;
import com.cms.model.User;

import java.util.ArrayList;
import java.util.List;


public   class ConvertUtils {



	private static UserDTO convertToUserDTO(User user) {
		UserDTO userDTO= new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		if(user.getUserInfo()!=null){
			
			userDTO.setFirstName(user.getUserInfo().getFirstName());
			userDTO.setLastName(user.getUserInfo().getLastName());
			userDTO.setStreetAddress(user.getUserInfo().getStreetAddress());
			userDTO.setVdcOrMunicipality(user.getUserInfo().getVdc());
			userDTO.setDistrict(user.getUserInfo().getDistrict());
			userDTO.setZone(user.getUserInfo().getZone());
			userDTO.setCountry(user.getUserInfo().getCountry());
			userDTO.setEmail(user.getUserInfo().getEmail());
			userDTO.setLandlineNumber(user.getUserInfo().getLandlineNumber());
			userDTO.setDob(user.getUserInfo().getDob().toString());
			userDTO.setMale(user.getUserInfo().isMale());
		}
		if(user.getRoles()!=null){
			userDTO.setRoles(user.getRoles());
		}
		
		return userDTO;
	}

	public static List<UserDTO> convertToUserDTOs(List<User> users) {
		List<UserDTO> dtos= new ArrayList<UserDTO>();
		for (User user : users) {
			dtos.add(ConvertUtils.convertToUserDTO(user));
		}
		return dtos;
	}


	 

	 


}
