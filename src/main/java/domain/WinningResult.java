package domain;

import domain.participant.Participants;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class WinningResult {

    private final Map<Player, WinningStatus> playersResult = new HashMap<>();
    private final Map<WinningStatus, Integer> dealerResult = new HashMap<>();

    public WinningResult(final Participants participants) {
        makeWinningResult(participants);
    }

    private void makeWinningResult(final Participants participants) {
        int dealerScore = participants.getDealer().calculateScore();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = decideWinningStatus(player, dealerScore);
            playersResult.put(player, playerWinningStatus);
            dealerResult.put(playerWinningStatus.reverse(),
                    dealerResult.getOrDefault(playerWinningStatus.reverse(), 0) + 1);
        }
    }
    private WinningStatus decideWinningStatus(final Player player, final int dealerScore) {
        int score = player.calculateScore();
        if (dealerScore > 21) {
            if (score > 21) {
                return WinningStatus.TIE;
            }
            return WinningStatus.WIN;
        }
        if (score <= 21 && score > dealerScore) {
            return WinningStatus.WIN;
        }
        if (score == dealerScore) {
            return WinningStatus.TIE;
        }
        return WinningStatus.LOSE;
    }

    public Map<Player, WinningStatus> getPlayersResult() {
        return playersResult;
    }

    public Map<WinningStatus, Integer> getDealerResult() {
        return dealerResult;
    }
}
