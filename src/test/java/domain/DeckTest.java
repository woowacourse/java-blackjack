package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    @DisplayName("0번째 위치의 카드를 반환한다")
    void drawFirstTest() {
        Deck deck = new Deck(() -> 0);

        assertThat(deck.draw()).isEqualTo(new Card(CardType.HEART, CardNumber.ACE));
    }

    @Test
    @DisplayName("51번째 위치의 카드를 반환한다")
    void drawLastTest() {
        Deck deck = new Deck(() -> 51);

        assertThat(deck.draw()).isEqualTo(new Card(CardType.DIAMOND, CardNumber.KING));
    }
}