package blackjack.domain.bet;

import blackjack.domain.player.PlayerName;

public class Bet {

    private final PlayerName playerName;
    private final Money betAmount;

    public Bet(PlayerName playerName, Money betAmount) {
        this.playerName = playerName;
        this.betAmount = betAmount;
    }

    public Money calculateMultipleMoneyOfBet(double scope) {
        return betAmount.multiply(scope);
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Money getBetAmount() {
        return betAmount;
    }
}
