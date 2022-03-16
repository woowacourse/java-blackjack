package blackjack.domain.state;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BustTest {

    @Test
    void 수익률이_마이너스1() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK)));
        final Bust bust = new Bust(cards);

        assertThat(bust.earningRate(bust)).isEqualTo(-1);
    }

    @ParameterizedTest
    @MethodSource("generateScoreValues")
    void 스코어_계산() {
        final Cards cards = new Cards(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK)));
        final Bust bust = new Bust(cards);

        assertThat(bust.score()).isEqualTo(30);
    }

    private static Stream<Arguments> generateScoreValues() {
        return Stream.of(
                Arguments.of(Set.of(Card.of(SPADES, KING), Card.of(SPADES, QUEEN), Card.of(SPADES, JACK)), 30),
                Arguments.of(Set.of(Set.of(Card.of(SPADES, SEVEN), Card.of(SPADES, EIGHT), Card.of(SPADES, A))), 26)
        );
    }
}
