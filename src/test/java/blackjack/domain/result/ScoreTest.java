package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @ParameterizedTest
    @MethodSource("provideForCards")
    @DisplayName("카드 합 계산")
    void calculateScore(List<Card> cards, int expectedScore) {
        Score actualScore = Score.from(cards);

        assertThat(actualScore.getValue()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> provideForCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.EIGHT)
                        ), 21
                ),

                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                                new Card(CardPattern.DIAMOND, CardNumber.TEN)
                        ), 15
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForCardsWithAce")
    @DisplayName("에이스 카드 계산")
    void calculateScoreWithAce(List<Card> cards) {
        Score score = Score.from(cards);

        assertThat(score.calculateWithAce().getValue()).isEqualTo(21);
    }

    private static Stream<Arguments> provideForCardsWithAce() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.KING)
                        )
                ),

                Arguments.of(
                        List.of(
                                new Card(CardPattern.DIAMOND, CardNumber.ACE),
                                new Card(CardPattern.DIAMOND, CardNumber.KING),
                                new Card(CardPattern.DIAMOND, CardNumber.JACK)
                        )
                )
        );
    }
}
