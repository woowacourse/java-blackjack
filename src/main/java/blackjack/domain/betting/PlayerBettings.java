package blackjack.domain.betting;

import blackjack.domain.result.WinningResult;
import java.util.List;
import java.util.Map;

public class PlayerBettings {
    private final List<PlayerBetting> playerBettings;

    public PlayerBettings(final List<PlayerBetting> playerBettings) {
        this.playerBettings = playerBettings;
    }

    public static PlayerBettings from(final Map<String, Integer> rawPlayerBettings) {
        List<PlayerBetting> playerBettings = rawPlayerBettings.entrySet().stream()
                .map(entry -> PlayerBetting.create(entry.getKey(), entry.getValue()))
                .toList();
        return new PlayerBettings(playerBettings);
    }

    public PlayerBettings applyWinStatus(final WinningResult winningResult) {
        List<PlayerBetting> bettingResults = winningResult.getParticipantsResult().entrySet().stream()
                .flatMap(entry -> playerBettings.stream()
                        .filter(playerBetting -> playerBetting.equalsName(entry.getKey()))
                        .map(playerBetting -> playerBetting.applyWinStatus(entry.getValue().getBetMultiplier())))
                .toList();

        return new PlayerBettings(bettingResults);
    }

    public List<PlayerBetting> getPlayerBettings() {
        return playerBettings;
    }
}
