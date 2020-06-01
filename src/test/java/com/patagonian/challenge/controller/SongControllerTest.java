package com.patagonian.challenge.controller;

import com.patagonian.challenge.dto.ArtistDto;
import com.patagonian.challenge.dto.ExternalUrlDto;
import com.patagonian.challenge.dto.SimpleSongDto;
import com.patagonian.challenge.dto.SongDto;
import com.patagonian.challenge.dto.SongsDto;
import com.patagonian.challenge.exception.NotFoundException;
import com.patagonian.challenge.service.SongService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SongControllerTest {

    @MockBean
    SongService songService;

    @LocalServerPort
	private int randomServerPort;

	@Autowired
	private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    public void setup() {
		baseUrl = "http://localhost:" + randomServerPort + "/api/v1/songs";
	}

    @Test
	void findAllByArtistName() throws URISyntaxException {

        SongsDto actualSongsDto = new SongsDto();
        actualSongsDto.setSongs(new ArrayList<SimpleSongDto>());
        actualSongsDto.getSongs().add(new SimpleSongDto("1AVu7Kc2MRrLfOG1RCEf07", "Californication"));
        actualSongsDto.getSongs().add(new SimpleSongDto("17UUYZ290omPaJY4wKeyHh", "Can't Stop"));
        actualSongsDto.getSongs().add(new SimpleSongDto("48zFZh27QU5qsrBjn4C2FA", "Bob"));
        when(songService.findAllByArtistName(any(String.class))).thenReturn(actualSongsDto);
		
		URI uri = new URI(baseUrl + "?artistName=Red Hot Chili Peppers".replaceAll(" ", "%20"));
		ResponseEntity<SongsDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<SongsDto>() {});
		SongsDto expectedSongsDto = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(expectedSongsDto).isNotNull();
        assertThat(expectedSongsDto.getSongs().size()).isEqualTo(3);
        assertThat(expectedSongsDto.getSongs()).containsExactly(
            new SimpleSongDto("1AVu7Kc2MRrLfOG1RCEf07", "Californication"),
            new SimpleSongDto("17UUYZ290omPaJY4wKeyHh", "Can't Stop"),
            new SimpleSongDto("48zFZh27QU5qsrBjn4C2FA", "Bob")
        );
    }

    @Test
	void findAllByArtistNameWithInvalidSize() throws URISyntaxException {

        URI uri = new URI(baseUrl + "?artistName=Re");
        ResponseEntity<SongsDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<SongsDto>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
	void findById() throws URISyntaxException {

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
        when(songService.findById(any(String.class))).thenReturn(actualSongDto);
		
		URI uri = new URI(baseUrl + "/1AVu7Kc2MRrLfOG1RCEf07");
		ResponseEntity<SongDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<SongDto>() {});
		SongDto expectedSongDto = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(expectedSongDto).isNotNull();
        assertThat(expectedSongDto).isEqualTo(actualSongDto);
    }

    @Test
	void findByIdNotFoundException() throws URISyntaxException {

        when(songService.findById(any(String.class))).thenThrow(new NotFoundException("Song not found"));

        URI uri = new URI(baseUrl + "/99999999999999999999");
        ResponseEntity<SongsDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<SongsDto>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND); 
    }

}