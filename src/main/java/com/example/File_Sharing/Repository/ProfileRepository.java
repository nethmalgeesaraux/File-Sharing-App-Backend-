package com.example.File_Sharing.Repository;

import com.example.File_Sharing.Documents.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository <ProfileDocument,String>{

    Optional<ProfileDocument> findByEmail(String email);

    ProfileDocument findByClerkId(String clerkId);

    boolean existsByClerkId(String clerkId);
}
