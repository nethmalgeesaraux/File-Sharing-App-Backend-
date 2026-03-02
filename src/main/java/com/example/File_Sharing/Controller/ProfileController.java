package com.example.File_Sharing.Controller;


import com.example.File_Sharing.Dto.ProfileDto;
import com.example.File_Sharing.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

 @RestController
 public class ProfileController {

     private ProfileService profileService;

     @Autowired
     public ProfileController(ProfileService profileService) {
         this.profileService = profileService;
     }

     @PostMapping("/register")
     public ResponseEntity<?> registerProfile(@RequestBody ProfileDto profileDto){
         HttpStatus status = profileService.existsByClerkId(profileDto.getClerkId()) ? HttpStatus.OK : HttpStatus.CREATED;
         ProfileDto savedProfile = profileService.createProfile(profileDto);
         return ResponseEntity.status(status).body(savedProfile);
     }
 }
