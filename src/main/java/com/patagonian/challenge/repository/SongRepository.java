package com.patagonian.challenge.repository;

import java.util.List;

import com.patagonian.challenge.model.Song;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {

    List<Song> findByArtists_Name(String artistName);

}