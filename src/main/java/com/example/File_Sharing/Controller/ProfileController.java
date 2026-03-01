package com.example.File_Sharing.Controller;


import com.example.File_Sharing.Dto.ProfileDto;
import com.example.File_Sharing.Service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProfile(@RequestBody ProfileDto profileDto){
        ProfileDto savedProfile = profileService.createProfile(profileDto);
        HttpStatus status = profileService.existsByClerkId(profileDto.getClerkId()) ? HttpStatus.OK : HttpStatus.CREATED;
        return ResponseEntity.status(status).body(savedProfile);
    }
}
