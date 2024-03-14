package blackjack.domain.bet;

import blackjack.domain.player.PlayerName;
import blackjack.domain.rule.BetResult;
import blackjack.domain.rule.GameResult;

public class Bet {

    private final PlayerName playerName;
    private final Money betAmount;

    public Bet(PlayerName playerName, Money betAmount) {
        this.playerName = playerName;
        this.betAmount = betAmount;
    }

    public BetResult calculateBetResult(GameResult gameResult) {
        Money earned = betAmount.multiply(gameResult.getProfitLeverage());
        return new BetResult(playerName.getValue(), earned);
    }

    public String getPlayerName() {
        return playerName.getValue();
    }

    public Money getBetAmount() {
        return betAmount;
    }
}
