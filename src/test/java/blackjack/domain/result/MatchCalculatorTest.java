package blackjack.domain.result;

import static blackjack.Fixture.SPADE_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

class MatchCalculatorTest {

    @DisplayName("플레이어가 버스트인 경우, 플레이어가 패배한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerLossIfPlayerBustTest")
    void playerLossIfPlayerBustTest(final Dealer dealer, final Player player) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.LOSS);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부이다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerDrawIfBothBlackjackTest")
    void playerDrawIfBothBlackjackTest(final Dealer dealer, final Player player) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면, 플레이어는 블랙잭으로 승리한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerWinIfOnlyPlayerBlackjackTest")
    void playerWinIfOnlyPlayerBlackjackTest(final Dealer dealer, final Player player) {
        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.BLACKJACK);
    }

    @DisplayName("둘다 블랙잭이 아닌 상태에서 동점이면 무승부이다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForPlayerDrawIfBothNotBlackjackTest")
    void playerDrawIfBothNotBlackjackTest(final Dealer dealer, final Player player) {
        player.drawCard(SPADE_ACE, false);

        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(MatchStatus.DRAW);
    }

    @DisplayName("플레이어가 스탠드인 상황에서, 플레이어의 승패를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 플레이어 : {2}")
    @MethodSource("blackjack.domain.result.provider.MatchCalculatorTestProvider#provideForJudgeMatchStatusIfPlayerStandTest")
    void judgeMatchStatusIfPlayerStandTest(final Dealer dealer,
                                           final Player player,
                                           final MatchStatus expectedMatchStatus) {
        player.drawCard(SPADE_ACE, false);

        final MatchStatus actualMatchStatus = MatchCalculator.judgeMatchStatusOfPlayer(player, dealer);
        assertThat(actualMatchStatus).isEqualTo(expectedMatchStatus);
    }

}
