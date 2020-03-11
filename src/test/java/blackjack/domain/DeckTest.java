package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @Test
    @DisplayName("덱을 다 소모했을 경우를 테스트")
    void emptyDeckTest() {
        Card card1 = new Card(Suit.CLUB, Symbol.SIX);
        Card card2 = new Card(Suit.HEART, Symbol.KING);
        Deck deck = new Deck(new LinkedList<>(Arrays.asList(card1, card2)));
        deck.drawCard();
        deck.drawCard();
        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("덱이 비었습니다");
    }
}
