package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @Test
    @DisplayName("덱의 점수를 구한다.")
    void getScore() {
        Deck deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.EIGHT));
        deck.add(new Card(Symbol.CLOVER, CardNumber.TWO));

        assertThat(deck.getScore()).isEqualTo(10);

        deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.ACE));

        assertThat(deck.getScore()).isEqualTo(11);

        deck = new Deck();
        deck.add(new Card(Symbol.CLOVER, CardNumber.ACE));
        deck.add(new Card(Symbol.CLOVER, CardNumber.KING));
        deck.add(new Card(Symbol.CLOVER, CardNumber.TWO));

        assertThat(deck.getScore()).isEqualTo(13);
    }
}
