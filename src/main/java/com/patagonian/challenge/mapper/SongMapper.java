package com.patagonian.challenge.mapper;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ArtistMapper.class)
public interface SongMapper {
  @Mapping(source = "externalUrl", target = "externalUrlDto")
  @Mapping(source = "artists", target = "artistDtos")
  SongDto songToSongDto(Song song);
}
