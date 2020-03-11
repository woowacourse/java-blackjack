package common;

import domain.card.PlayingCards;
import domain.gamer.Gamer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GamerDtoTest {
    private GamerDto gamerDto;

    @BeforeEach
    void setUp() {
        gamerDto = GamerDto.of(new Gamer(new PlayingCards(Collections.emptyList()), "testName"));
    }

    @Test
    void of() {
        assertThat(GamerDto.of(new Gamer(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    void getName() {
        assertThat(gamerDto.getName()).isEqualTo("testName");
    }
}