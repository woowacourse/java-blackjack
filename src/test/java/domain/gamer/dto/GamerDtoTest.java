package domain.gamer.dto;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GamerDtoTest {
    private GamerDto gamerDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerDto = GamerDto.of(new Player(playingCards, "testName", 1000));
    }

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThat(GamerDto.of(new Player(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    @DisplayName("getter - name")
    void getName() {
        assertThat(gamerDto.getName()).isEqualTo("testName");
    }

    @Test
    @DisplayName("getter - battingMoney")
    void getBattingMoney() {
        assertThat(gamerDto.getBattingMoney()).isEqualTo(1000);
    }
}
