package blackjack.domain;

import static blackjack.domain.card.Shape.CLOVER;
import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Shape.SPADE;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.EIGHT;
import static blackjack.domain.card.Value.JACK;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.SEVEN;
import static blackjack.domain.card.Value.SIX;
import static blackjack.domain.card.Value.TEN;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import blackjack.domain.card.Card;
import blackjack.domain.score.Score;
import blackjack.domain.score.GameResult;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreTest {
    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenBusted")
    @DisplayName("점수가 21점을 초과하면 버스트")
    void bustScoreTest(List<Card> cards, boolean expected) {
        Score score = new Score(cards);

        assertThat(score.isBusted()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenBusted() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(CLOVER, QUEEN), new Card(SPADE, TWO)), true),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, QUEEN), new Card(CLOVER, SEVEN), new Card(SPADE, ACE),
                                new Card(SPADE, ACE), new Card(SPADE, TEN)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenBlackJack")
    @DisplayName("두장의 카드로 점수가 21점이면 블랙잭")
    void blackJackScoreTest(List<Card> cards, boolean expected) {
        Score score = new Score(cards);

        assertThat(score.isBlackJack()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenBlackJack() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)), true),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(CLOVER, QUEEN), new Card(SPADE, ACE)), false)
        );
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenMaxScore")
    @DisplayName("점수가 21점이면 최고점수")
    void maxScoreScoreTest(List<Card> cards, boolean expected) {
        Score score = new Score(cards);

        assertThat(score.isMaxScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenMaxScore() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)), true),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(CLOVER, QUEEN), new Card(SPADE, ACE)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenGraterThanDealerMinimumScore")
    @DisplayName("17 이상의 카드인지 검사한다.")
    void graterThanDealerMinimumScoreScoreTest(List<Card> cards, boolean expected) {
        Score score = new Score(cards);

        assertThat(score.isLessThanDealerMinimumScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenGraterThanDealerMinimumScore() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SEVEN)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SIX)), true)
        );
    }

    @TestFactory
    @DisplayName("다른 핸드결과와 비교할 수 있다.")
    Collection<DynamicTest> competeTest() {
        return List.of(dynamicTest("18 vs 17", () -> {
                    Score score = new Score(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, EIGHT)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, SEVEN)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.WIN);
                }),
                dynamicTest("17 vs 18", () -> {
                    Score score = new Score(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SEVEN)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, EIGHT)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.LOSE);
                }),
                dynamicTest("18 vs 18", () -> {
                    Score score = new Score(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, EIGHT)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, EIGHT)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.DRAW);
                })
        );
    }

    @TestFactory
    @DisplayName("블랙잭일때 다른 핸드결과와 비교할 수 있다.")
    Collection<DynamicTest> competeWhenBlackJackTest() {
        return List.of(dynamicTest("blackjack vs blackjack", () -> {
                    Score score = new Score(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.DRAW);
                }),
                dynamicTest("blackjack vs max score", () -> {
                    Score score = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)));
                    Score otherScore = new Score(
                            List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.WIN);
                }),
                dynamicTest("max score vs blackjack", () -> {
                    Score score = new Score(
                            List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.LOSE);
                })
        );
    }

    @TestFactory
    @DisplayName("버스트일때 다른 핸드결과와 비교할 수 있다.")
    Collection<DynamicTest> competeWhenBustedTest() {
        return List.of(dynamicTest("busted vs busted", () -> {
                    Score score = new Score(
                            List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, KING)));
                    Score otherScore = new Score(
                            List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, KING)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.DRAW);
                }),
                dynamicTest("busted vs general", () -> {
                    Score score = new Score(
                            List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, KING)));
                    Score otherScore = new Score(List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.LOSE);
                }),
                dynamicTest("general vs busted", () -> {
                    Score score = new Score(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN)));
                    Score otherScore = new Score(
                            List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, KING)));
                    assertThat(score.compete(otherScore)).isEqualTo(GameResult.WIN);
                })
        );
    }

}
