package domain.gamer.dto;

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

class GamerWithScoreDtoTest {
    private GamerWithScoreDto gamerWithScoreDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerWithScoreDto = GamerWithScoreDto.of(new Player(playingCards, "testName"));
    }

    @Test
    void of() {
        assertThat(GamerWithCardsDto.of(new Player(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    void getName() {
        assertThat(gamerWithScoreDto.getName()).isEqualTo("testName");
    }

    @Test
    void getCards() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(gamerWithScoreDto.getCards().get(0)).isEqualTo(cards.get(0));
    }

    @Test
    void getScore() {
        List<Card> cards = Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER));
        assertThat(gamerWithScoreDto.getScore()).isEqualTo(10);
    }
}