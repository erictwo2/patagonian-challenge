package com.patagonian.challenge.repository;

import com.patagonian.challenge.model.Track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, String> {

}