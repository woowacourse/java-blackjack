package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.state.FinishState;
import blackjack.domain.participant.state.State;

public class MatchCalculator {

    private MatchCalculator() {
    }

    public static MatchStatus judgeMatchStatusOfPlayer(final Player player, final Dealer dealer) {
        validatePlayerStateIsFinished(player);
        validateDealerStateIsFinished(dealer);
        return judgeMatchStatusWithValidatedStates(player, dealer);
    }

    private static MatchStatus judgeMatchStatusWithValidatedStates(final Player player, final Dealer dealer) {
        final FinishState playerState = (FinishState) player.getState();
        final FinishState dealerState = (FinishState) dealer.getState();
        return playerState.judgeMatchStatus(dealerState);
    }

    private static void validatePlayerStateIsFinished(final Player player) {
        if (isNotFinished(player.getState())) {
            throw new IllegalStateException("플레이어의 턴이 종료되지 않았습니다.");
        }
    }

    private static void validateDealerStateIsFinished(final Dealer dealer) {
        if (isNotFinished(dealer.getState())) {
            throw new IllegalStateException("딜러의 턴이 종료되지 않았습니다.");
        }
    }

    private static boolean isNotFinished(final State state) {
        return !state.isFinished();
    }

}
