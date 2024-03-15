package blackjack.domain;

import blackjack.domain.dealer.Dealer;
import blackjack.domain.result.WinningResult;
import java.util.List;
import java.util.Map;

public class PlayerBettings {
    private final List<PlayerBetting> playerBettings;

    public PlayerBettings(final List<PlayerBetting> playerBettings) {
        this.playerBettings = playerBettings;
    }

    public static PlayerBettings from(final Map<String, Integer> rawPlayerBettings) {
        List<PlayerBetting> playerBettings = rawPlayerBettings.entrySet().stream().
                map(entry -> PlayerBetting.create(entry.getKey(), entry.getValue()))
                .toList();
        return new PlayerBettings(playerBettings);
    }

    public PlayerBettings applyWinStatus(final WinningResult winningResult) {
        List<PlayerBetting> bettingResults = winningResult.getParticipantsResult().entrySet().stream()
                .flatMap(entry -> playerBettings.stream()
                        .filter(playerBetting -> playerBetting.isName(entry.getKey()))
                        .map(playerBetting -> playerBetting.applyWinStatus(entry.getValue())))
                .toList();
        return new PlayerBettings(bettingResults);
    }

    public PlayerBetting getDealerResult() {
        int dealerProfit = playerBettings.stream()
                .mapToInt(PlayerBetting::getBetting)
                .sum();
        return new PlayerBetting(Dealer.DEALER_NAME, -dealerProfit);
    }

    public List<PlayerBetting> getPlayerBettings() {
        return playerBettings;
    }
}
