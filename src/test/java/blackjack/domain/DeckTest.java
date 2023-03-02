package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
                    new Card(CardShape.CLOVER, CardNumber.TEN),
                    new Card(CardShape.CLOVER, CardNumber.NINE),
                    new Card(CardShape.HEART, CardNumber.JACK));

    @Test
    @DisplayName("덱에서 첫번째 카드를 하나 뽑는다")
    void drawTest() {
        final Deck deck = new Deck(new TestDeckGenerator(testCards));

        final Card card = deck.draw();

        assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
    }

}
