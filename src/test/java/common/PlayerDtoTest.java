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
        PlayingCards playingCards1 = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));

        playerDto = PlayerDto.of(new Player("player1", 10000, playingCards1));
    }

    @Test
    void of() {
        PlayingCards playingCards1 = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        assertThat(PlayerDto.of(new Player("player1", 10000, playingCards1))).isNotNull();
    }

    @Test
    void getName() {
        assertThat(playerDto.getName()).isEqualTo("player1");
    }

    @Test
    void getCards() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(playerDto.getCards().get(0)).isEqualTo(cards.get(0));
    }
}