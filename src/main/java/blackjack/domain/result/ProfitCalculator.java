package blackjack.domain.result;

import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

import java.util.List;

public class ProfitCalculator {

    public static void calculateParticipantProfit(final Players players) {
        for (Player participant : players.getParticipants()) {
            Result participantResult = Result.competeResult(players.getDealer(), participant);
            calculateParticipantProfit(participant, participantResult);
        }
    }

    public static int calculateDealerProfit(List<Player> participants) {
        int profit = 0;
        for (Player participant : participants) {
            profit += calculateDealerProfit(participant);
        }
        return profit;
    }

    private static void calculateParticipantProfit(final Player player, final Result result) {
        validateParticipant(player);
        Participant participant = (Participant) player;
        calculate(participant, result);
    }

    private static void calculate(final Participant participant, final Result result) {
        if (result.isWin() && participant.isBlackjack()) {
            participant.increaseBlackjackBetting();
            return;
        }
        if (result.isWin()) {
            participant.increaseBetting();
        }
        if (result.isLose()) {
            participant.decreaseBetting();
        }
    }

    public static int calculateDealerProfit(final Player player) {
        validateParticipant(player);
        Participant participant = (Participant) player;
        return -participant.betting().profit();
    }

    private static void validateParticipant(final Player player) {
        if (!player.isParticipant()) {
            throw new IllegalArgumentException("[ERROR] 참가자가 아닙니다.");
        }
    }

}
