package blackjack.model.result;

import blackjack.model.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultTest {
    @ParameterizedTest
    @MethodSource("provideScoreAndExpectedMatchResult")
    @DisplayName("Score(점수)에 따라 승패를 결정한다.")
    public void decideByScoreTest(Score targetScore, MatchResult expectedResult) {
        // given
        Score otherScore = Score.from(9);

        // when
        MatchResult actualResult = MatchResult.decideByScore(targetScore, otherScore);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideScoreAndExpectedMatchResult() {
        return Stream.of(
                Arguments.of(Score.from(10), MatchResult.WIN),
                Arguments.of(Score.from(8), MatchResult.LOSE),
                Arguments.of(Score.from(9), MatchResult.PUSH)
        );
    }

    @Test
    @DisplayName("플레이어가 승리하면 베팅 금액만큼 금액을 더 받는다")
    void determineWinnerFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = BettingMoney.from(1000);

        // when
        BettingMoney finalBettingMoney = MatchResult.WIN.calculateFinalMoney(bettingMoney);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(2000);
    }

    @Test
    @DisplayName("플레이어가 지면 베팅 금액을 잃는다")
    void determineLoserFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = BettingMoney.from(1000);

        // when
        BettingMoney finalBettingMoney = MatchResult.LOSE.calculateFinalMoney(bettingMoney);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(0);
    }
}
