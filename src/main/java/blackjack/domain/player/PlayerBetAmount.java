package blackjack.domain.player;

import blackjack.domain.player.bet.BetAmount;

public final class PlayerBetAmount {

    private final PlayerName name;
    private BetAmount betAmount;

    public PlayerBetAmount(final PlayerName name) {
        this.name = name;
        this.betAmount = new BetAmount();
    }

    public PlayerName getName() {
        return name;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }

    // TODO : setter 열려도 되는가?
    public void setBetAmount(final BetAmount betAmount) {
        this.betAmount = betAmount;
    }
}
