package blackjack.domain.result;

import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static blackjack.domain.result.JudgeResult.BLACKJACK_WIN;
import static blackjack.domain.result.JudgeResult.LOSE;
import static blackjack.domain.result.JudgeResult.PUSH;
import static blackjack.domain.result.JudgeResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JudgeResultTest {

    @DisplayName("첫번째 수가 두번째 수보다 크면 승리이다.")
    @ParameterizedTest
    @CsvSource(value = {"-1:-2", "0:-1", "1:0", "2:1"}, delimiter = ':')
    void should_ReturnWin_When_SelfBiggerThanCounter(final int selfScore, final int counterScore) {
        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(WIN);
    }

    @DisplayName("첫번째 수가 두번째승 수보다 작으면 패배이다.")
    @ParameterizedTest
    @CsvSource(value = {"-2:-1", "-1:0", "0:1", "1:2"}, delimiter = ':')
    void should_ReturnLose_When_SelfSmallerThanCounter(final int selfScore, final int counterScore) {
        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(LOSE);
    }

    @DisplayName("첫번째 수와 두번째 수가 같으면 무승부이다.")
    @ParameterizedTest
    @CsvSource(value = {"-1:-1", "0:0", "1:1", "2:2"}, delimiter = ':')
    void should_ReturnPush_When_SelfSameWithCounter(final int selfScore, final int counterScore) {
        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(PUSH);
    }

    @DisplayName("주어진 판정 결과를 전달받아서 상대방의 결과를 반환한다.")
    @Test
    void should_ReturnCounterResult_When_GivenJudgeResult() {
        assertThat(BLACKJACK_WIN.counter()).isEqualTo(LOSE);
        assertThat(WIN.counter()).isEqualTo(LOSE);
        assertThat(PUSH.counter()).isEqualTo(PUSH);
        assertThat(LOSE.counter()).isEqualTo(WIN);
    }

    @DisplayName("각 결과마다 다르게 수익을 계산한다.")
    @Test
    void should_ReturnProfitRate_When_GivenJudgeResult() {
        assertThat(BLACKJACK_WIN.profit(BETTING_MONEY_1000)).isEqualTo(1500);
        assertThat(WIN.profit(BETTING_MONEY_1000)).isEqualTo(1000);
        assertThat(LOSE.profit(BETTING_MONEY_1000)).isEqualTo(-1000);
        assertThat(PUSH.profit(BETTING_MONEY_1000)).isEqualTo(0);
    }
}
