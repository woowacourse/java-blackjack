package blackjack.domain;

import static blackjack.domain.Number.JACK;
import static blackjack.domain.Number.TWO;
import static blackjack.domain.Suit.HEART;
import static blackjack.domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardsTest {

    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(TWO, SPADE), new Card(JACK, HEART)), 12),
                Arguments.of(List.of(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void 점수를_계산한다(final List<Card> cardPack, final int totalScore) {
        final Cards cards = new Cards(cardPack);

        assertThat(cards.calculateScore()).isEqualTo(totalScore);
    }
}

class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
