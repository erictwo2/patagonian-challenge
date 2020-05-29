package com.patagonian.challenge.controller;

import javax.validation.constraints.Size;

import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
@Validated
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    public SongsDto findAllByArtistName(@RequestParam @Size(min = 3) String artistName) {
        return songService.findAllByArtistName(artistName);
    }

    @GetMapping("/{songId}")
    public SongDto findById(@PathVariable String songId) {
        return songService.findById(songId);
    }

}