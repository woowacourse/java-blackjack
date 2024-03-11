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
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.player.Outcome;
import blackjack.domain.result.GameResult;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OutcomeTest {
    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenBusted")
    @DisplayName("점수가 21점을 초과하면 버스트")
    void bustOutcomeTest(List<Card> cards, boolean expected) {
        Outcome outcome = new Outcome(cards);

        assertThat(outcome.isBusted()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenBusted() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(CLOVER, QUEEN), new Card(SPADE, TWO)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatusWhenBlackJack")
    @DisplayName("두장의 카드로 점수가 21점이면 블랙잭")
    void blackJackOutcomeTest(List<Card> cards, boolean expected) {
        Outcome outcome = new Outcome(cards);

        assertThat(outcome.isBlackJack()).isEqualTo(expected);
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
    void maxScoreOutcomeTest(List<Card> cards, boolean expected) {
        Outcome outcome = new Outcome(cards);

        assertThat(outcome.isMaxScore()).isEqualTo(expected);
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
    void graterThanDealerMinimumScoreOutcomeTest(List<Card> cards, boolean expected) {
        Outcome outcome = new Outcome(cards);

        assertThat(outcome.isLessThanDealerMinimumScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> cardsAndBustStatusWhenGraterThanDealerMinimumScore() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SEVEN)), false),
                Arguments.arguments(
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SIX)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("twoCardsAndResult")
    @DisplayName("다른 핸드결과와 비교할 수 있다.")
    void competeTest(List<Card> cards, List<Card> otherCards, GameResult expected) {
        Outcome outcome = new Outcome(cards);
        Outcome otherOutcome = new Outcome(otherCards);

        GameResult gameResult = outcome.compete(otherOutcome);

        assertThat(gameResult).isEqualTo(expected);
    }

    private static Stream<Arguments> twoCardsAndResult() {
        return Stream.of(
                Arguments.arguments( // blackjack vs blackjack -> draw
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, ACE)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)),
                        GameResult.DRAW),
                Arguments.arguments( // blackjack vs max score -> win
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)),
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                        GameResult.WIN),
                Arguments.arguments( // max score vs blackjack -> lose
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, ACE)),
                        GameResult.LOSE),
                Arguments.arguments( // busted vs busted -> draw
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, KING)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, KING)),
                        GameResult.DRAW),
                Arguments.arguments( // busted vs general -> lose
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, KING)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN)),
                        GameResult.LOSE),
                Arguments.arguments( // general vs busted -> win
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, QUEEN), new Card(SPADE, KING)),
                        GameResult.WIN),
                Arguments.arguments( // 18 vs 17 -> win
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, EIGHT)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, SEVEN)),
                        GameResult.WIN),
                Arguments.arguments( // 17 vs 18 -> lose
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, SEVEN)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, EIGHT)),
                        GameResult.LOSE),
                Arguments.arguments( // 18 vs 18 -> draw
                        List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, EIGHT)),
                        List.of(new Card(SPADE, JACK), new Card(SPADE, EIGHT)),
                        GameResult.DRAW)
        );
    }
}

