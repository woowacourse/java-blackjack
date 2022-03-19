package blackjack.domain.result;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

class MatchCalculatorTest {

    @DisplayName("플레이어의 턴이 종료되지 않은 상태에서는 승패를 결정할 수 없어야 한다.")
    @Test
    void playerStateNotFinishedExceptionTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TWO));
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_ACE, SPADE_TEN));

        assertThatThrownBy(() -> MatchCalculator.judgeMatchStatusOfPlayer(player, dealer))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("플레이어의 턴이 종료되지 않았습니다.");
    }

    @DisplayName("딜러의 턴이 종료되지 않은 상태에서는 승패를 결정할 수 없어야 한다.")
    @Test
    void dealerStateNotFinishedExceptionTest() {
        final Player player = Player.readyToPlay("name", List.of(SPADE_ACE, SPADE_TEN));
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_ACE, SPADE_TWO));

        assertThatThrownBy(() -> MatchCalculator.judgeMatchStatusOfPlayer(player, dealer))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("딜러의 턴이 종료되지 않았습니다.");
    }

    @DisplayName("플레이어가 버스트인 경우, 플레이어가 패배한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerLossIfPlayerBustTest")
    void playerLossIfPlayerBustTest(final Player player, final Dealer dealer) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부이다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerDrawIfBothBlackjackTest")
    void playerDrawIfBothBlackjackTest(final Player player, final Dealer dealer) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면, 플레이어는 블랙잭으로 승리한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerWinIfOnlyPlayerBlackjackTest")
    void playerWinIfOnlyPlayerBlackjackTest(final Player player, final Dealer dealer) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.BLACKJACK);
    }

    @DisplayName("둘다 블랙잭이 아닌 상태에서 동점이면 무승부이다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerDrawIfBothNotBlackjackTest")
    void playerDrawIfBothNotBlackjackTest(final Player player, final Dealer dealer) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("플레이어가 스탠드인 상황에서, 플레이어의 승패를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 플레이어 : {2}")
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForJudgeMatchStatusIfPlayerStandTest")
    void judgeMatchStatusIfPlayerStandTest(final Player player,
                                           final Dealer dealer,
                                           final MatchStatus expectedMatchStatus) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(expectedMatchStatus);
    }

}
