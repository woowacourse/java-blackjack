package domain.result;

import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class WinningResult {

    private final Map<Player, WinningStatus> playersResult = new LinkedHashMap<>();

    public WinningResult(final Participants participants) {
        computeWinningResult(participants);
    }

    private void computeWinningResult(final Participants participants) {
        Dealer dealer = participants.getDealer();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = player.compete(dealer);
            playersResult.put(player, playerWinningStatus);
        }
    }

    public Map<Player, WinningStatus> getPlayersResult() {
        return Map.copyOf(playersResult);
    }

    public Map<WinningStatus, Integer> getDealerResult() {
        Map<WinningStatus, Integer> dealerResult = new EnumMap<>(WinningStatus.class);
        for (WinningStatus playerWinningStatus : playersResult.values()) {
            WinningStatus winningStatus = playerWinningStatus.reverse();
            dealerResult.put(winningStatus, dealerResult.getOrDefault(winningStatus, 0) + 1);
        }
        return dealerResult;
    }
}
