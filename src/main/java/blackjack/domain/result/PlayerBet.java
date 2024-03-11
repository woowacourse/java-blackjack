package blackjack.domain.result;

import blackjack.domain.participant.Player;

public class PlayerBet {
    private final Player player;
    private final BettingPrice bettingPrice;

    public PlayerBet(Player player, int bettingPrice) {
        this.player = player;
        this.bettingPrice = new BettingPrice(bettingPrice);
    }
}
