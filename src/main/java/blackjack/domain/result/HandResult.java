package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.matcher.*;

public enum HandResult {
    BLACKJACK_WIN(1.5, new PlayerBlackjackWinMatcher()),
    WIN(1, new PlayerWinMatcher()),
    LOSE(-1, new PlayerLoseMatcher()),
    DRAW(0, new PlayerDrawMatcher());

    private final double profitRate;
    private final PlayerResultMatcher playerResultMatcher;

    HandResult(double profitRate, PlayerResultMatcher playerResultMatcher) {
        this.profitRate = profitRate;
        this.playerResultMatcher = playerResultMatcher;
    }

    public boolean match(Player player, Dealer dealer) {
        return playerResultMatcher.isResultMatched(player, dealer);
    }

    public double getProfitRate() {
        return profitRate;
    }
}
