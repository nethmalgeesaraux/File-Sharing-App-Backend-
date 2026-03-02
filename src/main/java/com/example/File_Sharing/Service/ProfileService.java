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


    public boolean existsByClerkId(String clerkId) {
        return profileRepository.existsByClerkId(clerkId);
    }
}
