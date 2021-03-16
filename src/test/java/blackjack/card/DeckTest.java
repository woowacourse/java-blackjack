package blackjack.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    @DisplayName("정상적인 pop 테스트")
    @Test
    void popTest() {
        List<Card> cards = Collections.singletonList(new Card(Denomination.FIVE, Suit.CLOVER));
        Deck deck = new Deck(cards);
        assertThat(deck.pop()).isEqualTo(new Card(Denomination.FIVE, Suit.CLOVER));
    }

    @DisplayName("예외를 발생시키는 pop 테스트")
    @Test
    void popThrowExceptionTest() {
        List<Card> cards = Collections.singletonList(new Card(Denomination.FIVE, Suit.CLOVER));
        Deck deck = new Deck(cards);
        deck.pop();
        assertThatThrownBy(deck::pop).isInstanceOf(RuntimeException.class);
    }
}
