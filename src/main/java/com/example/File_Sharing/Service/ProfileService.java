package com.example.File_Sharing.Service;

import com.example.File_Sharing.Documents.ProfileDocument;
import com.example.File_Sharing.Dto.ProfileDto;
import com.example.File_Sharing.Repository.ProfileRepository;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDto createProfile(ProfileDto profileDto) {

        if (profileRepository.existsByClerkId(profileDto.getClerkId())) {
            return updateProfile(profileDto);
        }
        ProfileDocument profile = ProfileDocument.builder()
                .clerkId(profileDto.getClerkId())
                .email(profileDto.getEmail())
                .firstname(profileDto.getFirstname())
                .lastName(profileDto.getLastName())
                .photoUrl(profileDto.getPhotoUrl())
                .credits(5)
                .createdAt(Instant.now())
                .build();


        try {
            profile = profileRepository.save(profile);
        }catch (DuplicateKeyException e) {
            throw new RuntimeException("Email already exists");
        }


        return ProfileDto.builder()
                .id(profile.getId())
                .clerkId(profile.getClerkId())
                .email(profile.getEmail())
                .firstname(profile.getFirstname())
                .lastName(profile.getLastName())
                .photoUrl(profile.getPhotoUrl())
                .credits(profile.getCredits())
                .createdAt(profile.getCreatedAt())
                .build();

    }


    public ProfileDto updateProfile(ProfileDto profileDto){
        ProfileDocument existingProfile = profileRepository.findByClerkId(profileDto.getClerkId());
        if (existingProfile == null) {
            throw new RuntimeException("Profile not found for clerkId: " + profileDto.getClerkId());
        }

        existingProfile.setEmail(profileDto.getEmail());
        existingProfile.setFirstname(profileDto.getFirstname());
        existingProfile.setLastName(profileDto.getLastName());
        existingProfile.setPhotoUrl(profileDto.getPhotoUrl());

        try {
            existingProfile = profileRepository.save(existingProfile);
        }catch (DuplicateKeyException e) {
            throw new RuntimeException("Email already exists");
        }

        return ProfileDto.builder()
                .id(existingProfile.getId())
                .clerkId(existingProfile.getClerkId())
                .email(existingProfile.getEmail())
                .firstname(existingProfile.getFirstname())
                .lastName(existingProfile.getLastName())
                .photoUrl(existingProfile.getPhotoUrl())
                .credits(existingProfile.getCredits())
                .createdAt(existingProfile.getCreatedAt())
                .build();
    }

    public boolean existsByClerkId(String clerkId) {
        return profileRepository.existsByClerkId(clerkId);
    }

    public void deleteProfile(String clerkId) {
        ProfileDocument existingProfile = profileRepository.findByClerkId(clerkId);
        if (existingProfile == null) {
            profileRepository.delete(existingProfile);
        }

    }
}
