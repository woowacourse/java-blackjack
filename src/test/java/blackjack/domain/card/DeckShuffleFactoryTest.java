package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckShuffleFactoryTest {

    @DisplayName("모든 종류의 카드가 한 장씩만 덱에 포함된다.")
    @Test
    void createDistinctCards() {
        Stack<Card> expectedCards = new Stack<>();
        for (Number number : Number.values()) {
            for (Suit suit : Suit.values()) {
                expectedCards.push(new Card(number, suit));
            }
        }
        DeckFactory deckFactory = new DeckShuffleFactory();

        final Stack<Card> resultCards = deckFactory.generate();

        assertThat(resultCards).containsExactlyInAnyOrderElementsOf(expectedCards);
    }
}
