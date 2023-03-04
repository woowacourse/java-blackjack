package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.game.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("0번째 위치의 카드를 반환한다")
    void drawFirstTest() {
        Deck deck = new Deck((x) -> 0);

        assertThat(deck.serve()).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
    }

    @Test
    @DisplayName("51번째 위치의 카드를 반환한다")
    void drawLastTest() {
        Deck deck = new Deck((x) -> 51);

        assertThat(deck.serve()).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }
}