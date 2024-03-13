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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardTest {

    @ParameterizedTest
    @MethodSource("rankScoreStream")
    @DisplayName("성공: Card 객체는 올바른 점수를 가지고 있다")
    void score_NoException_WithMethodSource(Rank rank, Score score) {
        Card card = new Card(rank, DIAMOND);
        assertThat(card.score()).isEqualTo(score);
    }

    static Stream<Arguments> rankScoreStream() {
        return Stream.of(
            arguments(KING, Score.valueOf(10)),
            arguments(QUEEN, Score.valueOf(10)),
            arguments(JACK, Score.valueOf(10)),
            arguments(TEN, Score.valueOf(10)),
            arguments(NINE, Score.valueOf(9)),
            arguments(EIGHT, Score.valueOf(8)),
            arguments(SEVEN, Score.valueOf(7)),
            arguments(SIX, Score.valueOf(6)),
            arguments(FIVE, Score.valueOf(5)),
            arguments(FOUR, Score.valueOf(4)),
            arguments(THREE, Score.valueOf(3)),
            arguments(TWO, Score.valueOf(2)),
            arguments(ACE, Score.valueOf(1))
        );
    }
}
