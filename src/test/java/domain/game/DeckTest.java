package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;
import domain.game.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("0번째 위치의 카드를 반환한다")
    void drawFirstTest() {
        Deck deck = new Deck((x) -> {});

        Card drawnCard = deck.serve();

        assertThat(drawnCard.getType()).isEqualTo(CardType.HEART);
        assertThat(drawnCard.getNumber()).isEqualTo(CardNumber.ACE);
    }

    @Test
    @DisplayName("51번째 위치의 카드를 반환한다")
    void drawLastTest() {
        Deck deck = new Deck(Collections::reverse);

        Card drawnCard = deck.serve();

        assertThat(drawnCard.getType()).isEqualTo(CardType.DIAMOND);
        assertThat(drawnCard.getNumber()).isEqualTo(CardNumber.KING);
    }
}
