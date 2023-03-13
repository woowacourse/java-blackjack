package blackjack.domain.result;

import static blackjack.domain.ParticipantFixtures.BETTING_MONEY_1000;
import static blackjack.domain.ParticipantFixtures.DEALER_17;
import static blackjack.domain.ParticipantFixtures.DEALER_BLACKJACK;
import static blackjack.domain.ParticipantFixtures.DEALER_BUST;
import static blackjack.domain.ParticipantFixtures.PLAYER_16;
import static blackjack.domain.ParticipantFixtures.PLAYER_17;
import static blackjack.domain.ParticipantFixtures.PLAYER_20;
import static blackjack.domain.ParticipantFixtures.PLAYER_BLACKJACK;
import static blackjack.domain.ParticipantFixtures.PLAYER_BUST;
import static blackjack.domain.result.JudgeResult.BLACKJACK_WIN;
import static blackjack.domain.result.JudgeResult.LOSE;
import static blackjack.domain.result.JudgeResult.PUSH;
import static blackjack.domain.result.JudgeResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JudgeResultTest {

//    @DisplayName("첫번째 수가 두번째 수보다 크면 승리이다.")
//    @ParameterizedTest
//    @CsvSource(value = {"-1:-2", "0:-1", "1:0", "2:1"}, delimiter = ':')
//    void should_ReturnWin_When_SelfBiggerThanCounter(final int selfScore, final int counterScore) {
//        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(WIN);
//    }
//
//    @DisplayName("첫번째 수가 두번째승 수보다 작으면 패배이다.")
//    @ParameterizedTest
//    @CsvSource(value = {"-2:-1", "-1:0", "0:1", "1:2"}, delimiter = ':')
//    void should_ReturnLose_When_SelfSmallerThanCounter(final int selfScore, final int counterScore) {
//        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(LOSE);
//    }
//
//    @DisplayName("첫번째 수와 두번째 수가 같으면 무승부이다.")
//    @ParameterizedTest
//    @CsvSource(value = {"-1:-1", "0:0", "1:1", "2:2"}, delimiter = ':')
//    void should_ReturnPush_When_SelfSameWithCounter(final int selfScore, final int counterScore) {
//        assertThat(JudgeResult.matchWithoutBlackJackConsider(selfScore, counterScore)).isEqualTo(PUSH);
//    }

    @DisplayName("플레이어가 버스트이면 플레이어는 무조건 패배이다.")
    @Test
    void should_ReturnLose_When_PlayerIsBust() {
        assertThat(JudgeResult.of(PLAYER_BUST, DEALER_17)).isEqualTo(LOSE);
    }

    @DisplayName("플레이어가 버스트가 아닐 때 딜러가 버스트이면 플레이어는 무조건 일반 승리이다.")
    @Test
    void should_ReturnWin_When_DealerIsBust() {
        assertThat(JudgeResult.of(PLAYER_BLACKJACK, DEALER_BUST)).isEqualTo(WIN);
        assertThat(JudgeResult.of(PLAYER_20, DEALER_BUST)).isEqualTo(WIN);
        assertThat(JudgeResult.of(PLAYER_BUST, DEALER_17)).isEqualTo(LOSE);
    }

    @DisplayName("딜러가 버스트가 아니고, 플레이어만 블랙잭이면 플레이어는 블랙잭 승리이다.")
    @Test
    void should_ReturnBlackJackWin_When_OnlyPlayerIsBlackJack() {
        assertThat(JudgeResult.of(PLAYER_BLACKJACK, DEALER_17)).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("둘 다 버스트가 아니고, 플레이어만 블랙잭이 아닌 경우에는 점수를 비교해 결과를 정한다.")
    @Test
    void should_ReturnResultByCompare_When_BothNotBustedAndPlayerIsNotBlackJack() {
        assertThat(JudgeResult.of(PLAYER_BLACKJACK, DEALER_BLACKJACK)).isEqualTo(PUSH);
        assertThat(JudgeResult.of(PLAYER_20, DEALER_17)).isEqualTo(WIN);
        assertThat(JudgeResult.of(PLAYER_17, DEALER_17)).isEqualTo(PUSH);
        assertThat(JudgeResult.of(PLAYER_16, DEALER_17)).isEqualTo(LOSE);
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
