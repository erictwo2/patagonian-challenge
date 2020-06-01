package com.patagonian.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.patagonian.challenge.dto.ArtistDto;
import com.patagonian.challenge.dto.ExternalUrlDto;
import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.exception.NotFoundException;
import com.patagonian.challenge.mapper.SimpleSongMapper;
import com.patagonian.challenge.mapper.SongMapper;
import com.patagonian.challenge.model.Song;
import com.patagonian.challenge.repository.SongRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Assertions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SongServiceImpleTest {

    @Mock
    SongRepository songRepository;

    @Mock
    SimpleSongMapper simpleSongMapper;

    @Mock
    SongMapper songMapper;

    @InjectMocks
    SongServiceImple songService;

    @Test
	void songServiceLoaded() {
        assertNotNull(songService);
    }

    @Test
	void findAllByArtistName() {

        SongsDto expected = new SongsDto();
        expected.setSongs(new ArrayList<SimpleSongDto>());
        expected.getSongs().add(new SimpleSongDto("1AVu7Kc2MRrLfOG1RCEf07", "Californication"));
        expected.getSongs().add(new SimpleSongDto("17UUYZ290omPaJY4wKeyHh", "Can't Stop"));
        expected.getSongs().add(new SimpleSongDto("48zFZh27QU5qsrBjn4C2FA", "Bob"));

        when(songRepository.findByArtists_Name(any(String.class))).thenReturn(new ArrayList<>());
        when(simpleSongMapper.songsToSimpleSongDtos(any(List.class))).thenReturn(expected.getSongs());

        SongsDto result = songService.findAllByArtistName("Red Hot Chili Peppers");

        assertNotNull(result);
        assertEquals(result.getSongs().size(), 3);
        assertTrue(result.getSongs().equals(expected.getSongs()));
    }

    @Test
	void findById() {

        Song expected = new Song();
        SongDto expectedDto = new SongDto();
        expectedDto.setId("1AVu7Kc2MRrLfOG1RCEf07");
        expectedDto.setName("Californication");
        expectedDto.setType("track");
        expectedDto.setExplicit(false);
        expectedDto.setHref("https://api.spotify.com/v1/tracks/1AVu7Kc2MRrLfOG1RCEf07");
        expectedDto.setUri("spotify:track:1AVu7Kc2MRrLfOG1RCEf07");
        expectedDto.setTrackNumber(1);
        expectedDto.setDiscNumber(1);
        expectedDto.setDurationMs(187653l);
        expectedDto.setExternalUrlDto(new ExternalUrlDto("https://open.spotify.com/track/1AVu7Kc2MRrLfOG1RCEf07"));
        expectedDto.setLocal(false);
        expectedDto.setPlayable(null);
        expectedDto.setPreviewUrl("https://p.scdn.co/mp3-preview/c44e64aa965da19475a0fc2fc1dfb9c4ea94c191?cid=7ade872bdb9e43dbb376145736fe2384");
        
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId("1i8SpTcr7yvPOmcqrbnVXY");
        artistDto.setName("Red Hot Chili Peppers");
        artistDto.setType("artist");
        artistDto.setHref("https://api.spotify.com/v1/artists/1i8SpTcr7yvPOmcqrbnVXY");
        artistDto.setUri("spotify:artist:1i8SpTcr7yvPOmcqrbnVXY");

        expectedDto.setArtistDtos(new ArrayList<ArtistDto>());
        expectedDto.getArtistDtos().add(artistDto);
        expectedDto.setExternalUrlDto(new ExternalUrlDto("https://open.spotify.com/artist/1i8SpTcr7yvPOmcqrbnVXY"));

        when(songRepository.findById(any(String.class))).thenReturn(Optional.of(expected));
        when(songMapper.songToSongDto(any(Song.class))).thenReturn(expectedDto);

        SongDto result = songService.findById("1AVu7Kc2MRrLfOG1RCEf07");

        assertNotNull(result);
        assertEquals(result.getId(), expectedDto.getId());
    }

    @Test
	void findByIdNotFoundException() {

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> {
            when(songRepository.findById(any(String.class))).thenReturn(Optional.empty());
            songService.findById("99999999999999999999");
		});

		assertEquals(ex.getMessage(), "Song not found");
    }

}