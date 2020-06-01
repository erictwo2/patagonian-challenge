package com.patagonian.challenge.controller;

import com.patagonian.challenge.service.SongService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SongControllerTest {

    @Mock
    SongService songService;

    @InjectMocks
    SongController songController;

    @Test
	void controllerLoad() {
        assertThat(songController).isNotNull();
    }

}