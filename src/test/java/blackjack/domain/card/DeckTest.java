package blackjack.domain.card;

import blackjack.domain.card.generator.TestDeckGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    private final Card spadeAceCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card cloverTenCard = new Card(CardShape.CLOVER, CardNumber.TEN);
    private final Card cloverNineCard = new Card(CardShape.CLOVER, CardNumber.NINE);
    private final Card heartJackCard = new Card(CardShape.HEART, CardNumber.JACK);

    private final List<Card> testCards = List.of(spadeAceCard, cloverTenCard, cloverNineCard, heartJackCard);


    @Test
    @DisplayName("덱에서 첫번째 카드를 하나 뽑는다")
    void drawTest() {
        final Deck deck = new Deck(new TestDeckGenerator(testCards));

        final Card card = deck.draw();

        assertThat(card.getShape()).isEqualTo(CardShape.SPADE);
    }

}
