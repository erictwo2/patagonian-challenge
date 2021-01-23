package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.model.Song;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface SimpleSongMapper {
  @Mapping(source = "id", target = "songId")
  @Mapping(source = "name", target = "songTitle")
  @Named("songToSimpleSongDto")
  SimpleSongDto songToSimpleSongDto(Song song);

  @IterableMapping(qualifiedByName = "songToSimpleSongDto")
  List<SimpleSongDto> songsToSimpleSongDtos(List<Song> songs);
}
