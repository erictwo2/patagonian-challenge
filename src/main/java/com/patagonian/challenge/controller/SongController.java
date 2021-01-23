package com.patagonian.challenge.controller;

import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.service.SongService;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class SongController {
  @Autowired
  private SongService songService;

  @GetMapping("/v1/songs")
  public SongsDto findAllByArtistName(@RequestParam @Size(min = 3) String artistName) {
    return songService.findAllByArtistName(artistName);
  }

  @GetMapping("/v2/songs")
  public Slice<SimpleSongDto> findAllByArtistName(
    @RequestParam @Size(min = 3) String artistName,
    @RequestParam(defaultValue = "0") @Min(0) Integer page,
    @RequestParam(defaultValue = "10") @Min(5) @Max(50) Integer size,
    @RequestParam(defaultValue = "asc") String sort
  ) {
    return songService.findAllByArtistName(artistName, page, size, sort);
  }

  @GetMapping({ "/v1/songs/{songId}", "/v2/songs/{songId}" })
  public SongDto findById(@PathVariable String songId) {
    return songService.findById(songId);
  }
}
