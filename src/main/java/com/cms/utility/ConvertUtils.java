package com.cms.utility;

import com.cms.dto.*;
import com.cms.model.*;

import java.util.ArrayList;
import java.util.List;


public class ConvertUtils {


    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        if (user.getUserInfo() != null) {

            userDTO.setFirstName(user.getUserInfo().getFirstName());
            userDTO.setLastName(user.getUserInfo().getLastName());
            userDTO.setMiddleName(user.getUserInfo().getMiddleName());
            userDTO.setStreetAddress(user.getUserInfo().getStreetAddress());
            userDTO.setVdcOrMunicipality(user.getUserInfo().getVdc());
            userDTO.setDistrict(user.getUserInfo().getDistrict());
            userDTO.setZone(user.getUserInfo().getZone());
            userDTO.setCountry(user.getUserInfo().getCountry());
            userDTO.setEmail(user.getUserInfo().getEmail());
            userDTO.setLandlineNumber(user.getUserInfo().getLandlineNumber());
            userDTO.setMobileNumber(user.getUserInfo().getMobileNumber());
            userDTO.setDob(user.getUserInfo().getDob().toString());
            userDTO.setMale(user.getUserInfo().isMale());
        }
        if (user.getRoles() != null) {
            userDTO.setRoles(user.getRoles());
        }

        return userDTO;
    }

    public static List<UserDTO> convertToUserDTOs(List<User> users) {
        List<UserDTO> dtos = new ArrayList<UserDTO>();
        for (User user : users) {
            dtos.add(ConvertUtils.convertToUserDTO(user));
        }
        return dtos;
    }

    public static ProjectDTO convertToProjectDTO(Project p) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(p.getId());
        dto.setProjectCode(p.getProjectCode());
        dto.setFiscalYear(p.getFiscalYear());
        dto.setBudgetSubHeadNumber(p.getBudgetSubHeadNumber());
        dto.setAidOrganisation(p.getAidOrganisation());
        dto.setBudget(p.getBudget());
        return dto;
    }

    public static List<ProjectDTO> convertToProjectDTOs(List<Project> projects) {
        List<ProjectDTO> dtos = new ArrayList<ProjectDTO>();
        for (Project project : projects) {
            dtos.add(ConvertUtils.convertToProjectDTO(project));
        }
        return dtos;
    }


    public static List<TrainingCenterDTO> convertToTrainingCenterDTOs(List<TrainingCenter> tcs) {
        List<TrainingCenterDTO> dtos = new ArrayList<TrainingCenterDTO>();
        for (TrainingCenter tc : tcs) {
            dtos.add(ConvertUtils.convertToTrainingCenterDTO(tc));
        }
        return dtos;

    }

    public static TrainingCenterDTO convertToTrainingCenterDTO(TrainingCenter tc) {
        TrainingCenterDTO dto = new TrainingCenterDTO();
        dto.setId(tc.getId());
        dto.setName(tc.getName());
        dto.setAddress(tc.getAddress());
        dto.setDistrict(tc.getDistrict());
        dto.setZone(tc.getZone());
        if(tc.getParentTrainingCenter()!=null) {
            dto.setParentTrainingCenter(tc.getParentTrainingCenter().getName());
        }
        return dto;
    }

    public static List<TrainingDTO> convertToTrainingDTOs(List<Training> trainings) {
        List<TrainingDTO> dtos = new ArrayList<TrainingDTO>();
        for (Training t : trainings) {
            dtos.add(ConvertUtils.convertToTrainingDTO(t));
        }
        return dtos;
    }


    public static TrainingDTO convertToTrainingDTO(Training t) {
        TrainingDTO dto=new TrainingDTO();
        dto.setId(t.getId());
        dto.setName(t.getName());
        dto.setBudget(t.getBudget());
        dto.setStart(t.getStartDate().toString());
        dto.setEnd(t.getEndDate().toString());
        dto.setTarget(t.getTarget());
        return dto;

    }



    public static List<MemberDTO> convertToMemberDTOs(List<Member> members) {
        List<MemberDTO> dtos = new ArrayList<MemberDTO>();
        for (Member m : members) {
            dtos.add(ConvertUtils.convertToMemberDTO(m));
        }
        return dtos;


    }

    public static MemberDTO convertToMemberDTO(Member m) {
        MemberDTO dto= new MemberDTO();
        dto.setMemberId(m.getId());
        dto.setFirstName(m.getFirstName());
        dto.setMiddleName(m.getMiddleName());
        dto.setLastName(m.getLastName());
        dto.setMobileNumber(m.getMobileNumber());
        dto.setLandlineNumber(m.getLandlineNumber());
        dto.setEmail(m.getEmail());
        dto.setStreetAddress(m.getStreetAddress());
        dto.setVdcOrMunicipality(m.getVdcOrMunicipality());
        dto.setDistrict(m.getDistrict());
        dto.setZone(m.getZone());
        dto.setCountry(m.getCountry());
        dto.setDob(m.getDob());
        dto.setWorkplace(m.getWorkplace());
        dto.setOrganisation(m.getOrganisation());
        dto.setMemberType(m.getMemberType());
        dto.setTrainingId(m.getTraining().getId());
        dto.setMale(m.isMale());
        return dto;
    }
}

