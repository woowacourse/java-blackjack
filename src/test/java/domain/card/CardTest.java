package domain.card;

import static domain.card.Rank.ACE;
import static domain.card.Rank.EIGHT;
import static domain.card.Rank.FIVE;
import static domain.card.Rank.FOUR;
import static domain.card.Rank.JACK;
import static domain.card.Rank.KING;
import static domain.card.Rank.NINE;
import static domain.card.Rank.QUEEN;
import static domain.card.Rank.SEVEN;
import static domain.card.Rank.SIX;
import static domain.card.Rank.TEN;
import static domain.card.Rank.THREE;
import static domain.card.Rank.TWO;
import static domain.card.Symbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest
    @MethodSource("rankScoreStream")
    void score_NoException_WithMethodSource(Rank rank, int score) {
        Card card = new Card(rank, DIAMOND);
        assertThat(card.score()).isEqualTo(score);
    }

    static Stream<Arguments> rankScoreStream() {
        return Stream.of(
            arguments(KING, 10), arguments(QUEEN, 10), arguments(JACK, 10), arguments(TEN, 10),
            arguments(NINE, 9), arguments(EIGHT, 8), arguments(SEVEN, 7),
            arguments(SIX, 6), arguments(FIVE, 5), arguments(FOUR, 4),
            arguments(THREE, 3), arguments(TWO, 2), arguments(ACE, 1)
        );
    }
}
