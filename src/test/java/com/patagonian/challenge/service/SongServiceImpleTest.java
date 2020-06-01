package com.patagonian.challenge.service;

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

import static org.assertj.core.api.Assertions.*;

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
        assertThat(songService).isNotNull();
    }

    @Test
	void findAllByArtistName() {

        SongsDto actualSongDto = new SongsDto();
        actualSongDto.setSongs(new ArrayList<SimpleSongDto>());
        actualSongDto.getSongs().add(new SimpleSongDto("1AVu7Kc2MRrLfOG1RCEf07", "Californication"));
        actualSongDto.getSongs().add(new SimpleSongDto("17UUYZ290omPaJY4wKeyHh", "Can't Stop"));
        actualSongDto.getSongs().add(new SimpleSongDto("48zFZh27QU5qsrBjn4C2FA", "Bob"));
        when(songRepository.findByArtists_Name(any(String.class))).thenReturn(new ArrayList<>());
        when(simpleSongMapper.songsToSimpleSongDtos(any(List.class))).thenReturn(actualSongDto.getSongs());

        SongsDto expectedSongDto = songService.findAllByArtistName("Red Hot Chili Peppers");

        assertThat(expectedSongDto).isNotNull();
        assertThat(expectedSongDto.getSongs().size()).isEqualTo(3);
        assertThat(expectedSongDto.getSongs()).containsExactly(
            new SimpleSongDto("1AVu7Kc2MRrLfOG1RCEf07", "Californication"),
            new SimpleSongDto("17UUYZ290omPaJY4wKeyHh", "Can't Stop"),
            new SimpleSongDto("48zFZh27QU5qsrBjn4C2FA", "Bob")
        );
    }

    @Test
	void findById() {

        Song actualSong = new Song();
        SongDto actualSongDto = new SongDto();
        actualSongDto.setId("1AVu7Kc2MRrLfOG1RCEf07");
        actualSongDto.setName("Californication");
        actualSongDto.setType("track");
        actualSongDto.setExplicit(false);
        actualSongDto.setHref("https://api.spotify.com/v1/tracks/1AVu7Kc2MRrLfOG1RCEf07");
        actualSongDto.setUri("spotify:track:1AVu7Kc2MRrLfOG1RCEf07");
        actualSongDto.setTrackNumber(1);
        actualSongDto.setDiscNumber(1);
        actualSongDto.setDurationMs(187653l);
        actualSongDto.setExternalUrlDto(new ExternalUrlDto("https://open.spotify.com/track/1AVu7Kc2MRrLfOG1RCEf07"));
        actualSongDto.setLocal(false);
        actualSongDto.setPlayable(null);
        actualSongDto.setPreviewUrl("https://p.scdn.co/mp3-preview/c44e64aa965da19475a0fc2fc1dfb9c4ea94c191?cid=7ade872bdb9e43dbb376145736fe2384");
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId("1i8SpTcr7yvPOmcqrbnVXY");
        artistDto.setName("Red Hot Chili Peppers");
        artistDto.setType("artist");
        artistDto.setHref("https://api.spotify.com/v1/artists/1i8SpTcr7yvPOmcqrbnVXY");
        artistDto.setUri("spotify:artist:1i8SpTcr7yvPOmcqrbnVXY");
        actualSongDto.setArtistDtos(new ArrayList<ArtistDto>());
        actualSongDto.getArtistDtos().add(artistDto);
        actualSongDto.setExternalUrlDto(new ExternalUrlDto("https://open.spotify.com/artist/1i8SpTcr7yvPOmcqrbnVXY"));
        when(songRepository.findById(any(String.class))).thenReturn(Optional.of(actualSong));
        when(songMapper.songToSongDto(any(Song.class))).thenReturn(actualSongDto);

        SongDto expectedSongDto = songService.findById("1AVu7Kc2MRrLfOG1RCEf07");

        assertThat(expectedSongDto).isNotNull();
        assertThat(expectedSongDto).isEqualTo(actualSongDto);
    }

    @Test
	void findByIdNotFoundException() {

        when(songRepository.findById(any(String.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {    
            songService.findById("99999999999999999999");
        }).withMessage("Song not found");
 
    }

}