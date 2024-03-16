package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckShuffleFactoryTest {

    @DisplayName("모든 종류의 카드가 한 장씩만 덱에 포함된다.")
    @Test
    void createDistinctCards() {
        final List<Card> cards = Stream.of(Number.values())
                                .flatMap(number -> Stream.of(Suit.values())
                                .map(suit -> new Card(number, suit)))
                                .toList();
        final Stack<Card> expectedCards = new Stack<>();
        expectedCards.addAll(cards);

        DeckFactory deckFactory = new ShuffledDeckFactory();
        final Stack<Card> resultCards = deckFactory.generate();

        assertThat(resultCards).containsExactlyInAnyOrderElementsOf(expectedCards);
    }
}
