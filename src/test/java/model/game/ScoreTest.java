package model.game;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.NINE;
import static model.card.CardNumber.QUEEN;
import static model.card.CardNumber.THREE;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreTest {

    @DisplayName("카드 합이 21 또는 21에 가깝게 스코어를 계산한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedScore")
    void testCalculateGameScore(List<Card> cards, int expectedScore) {
        Score playerScore = Score.from(cards);
        assertThat(playerScore.getValue()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideCardsAndExpectedScore() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), 21
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), 13
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)), 22
            )
        );
    }

    @DisplayName("카드 합이 21인지 여부를 반환한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedIs21")
    void testScoreIs21(List<Card> cards, boolean expected) {
        Score score = Score.from(cards);
        assertThat(score.is21()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedIs21() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), true
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), false
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)),
                false
            )
        );
    }

    @DisplayName("카드 합이 21초과 여부를 반환한다")
    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedIsOver21")
    void testScoreIsOver21(List<Card> cards, boolean expected) {
        Score score = Score.from(cards);
        assertThat(score.isOver21()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardsAndExpectedIsOver21() {
        return Stream.of(
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(JACK, HEART)), false
            ),
            Arguments.of(
                List.of(new Card(ACE, SPADE), new Card(ACE, SPADE), new Card(ACE, SPADE)), false
            ),
            Arguments.of(
                List.of(new Card(THREE, SPADE), new Card(NINE, SPADE), new Card(QUEEN, HEART)), true
            )
        );
    }
}
