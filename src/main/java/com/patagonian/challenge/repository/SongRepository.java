package com.patagonian.challenge.repository;

import com.patagonian.challenge.model.Song;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
  List<Song> findByArtists_NameIgnoreCaseOrderByNameAsc(String artistName);

  Slice<Song> findByArtists_NameIgnoreCase(String artistName, Pageable pageable);
}
