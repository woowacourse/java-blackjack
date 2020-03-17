package common;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerDtoTest {
    private PlayerDto playerDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        playerDto = PlayerDto.of(new Player(playingCards, "testName"));
    }

    @Test
    void of() {
        assertThat(PlayerDto.of(new Player(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    void getName() {
        assertThat(playerDto.getName()).isEqualTo("testName");
    }

    @Test
    void getCards() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(playerDto.getCards().get(0)).isEqualTo(cards.get(0));
    }
}