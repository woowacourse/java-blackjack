package card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("덱에서 카드를 뽑는다.")
    void createDeckTest() {
        // given
        List<Card> cards = List.of(
                new Card(Symbol.HEART, Number.ACE),
                new Card(Symbol.CLOVER, Number.EIGHT),
                new Card(Symbol.DIAMOND, Number.JACK)
        );
        Deck deck = new Deck(cards);
        Card expected = new Card(Symbol.HEART, Number.ACE);
        // when, then
        assertThat(deck.draw()).isEqualTo(expected);
    }
}
