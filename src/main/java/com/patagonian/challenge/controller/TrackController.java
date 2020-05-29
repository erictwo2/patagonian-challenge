package com.patagonian.challenge.controller;

import javax.validation.constraints.Size;

import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracks")
@Validated
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public SongsDto findAllByArtistName(@RequestParam @Size(min = 3) String artistName) {
        return trackService.findAllByArtistName(artistName);
    }

}