package blackjack.domain.player;

import blackjack.domain.bet.BetAmount;

public record PlayerBetAmount(PlayerName name, BetAmount betAmount) {

    public PlayerBetAmount(PlayerName name) {
        this(name, new BetAmount(0));
    }
}
