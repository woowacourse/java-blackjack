package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.state.PlayState;
import blackjack.domain.participant.state.finished.FinishedState;

public class MatchCalculator {

    private MatchCalculator() {
    }

    public static MatchStatus judgeMatchStatusOfPlayer(final Player player, final Dealer dealer) {
        validatePlayerStateFinished(player);
        validateDealerStateFinished(dealer);
        return judgeMatchStatusWithValidatedStates(player, dealer);
    }

    private static MatchStatus judgeMatchStatusWithValidatedStates(final Player player, final Dealer dealer) {
        final FinishedState playerState = (FinishedState) player.getState();
        final FinishedState dealerState = (FinishedState) dealer.getState();
        return playerState.judgeMatchStatus(dealerState);
    }

    private static void validatePlayerStateFinished(final Player player) {
        if (isNotInstanceOf(player.getState())) {
            throw new IllegalStateException("플레이어의 턴이 종료되지 않았습니다.");
        }
    }

    private static void validateDealerStateFinished(final Dealer dealer) {
        if (isNotInstanceOf(dealer.getState())) {
            throw new IllegalStateException("딜러의 턴이 종료되지 않았습니다.");
        }
    }

    private static boolean isNotInstanceOf(final PlayState state) {
        return !(state instanceof FinishedState);
    }

}
