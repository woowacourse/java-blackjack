package blackjack.domain.betting2;

import blackjack.domain.hand.CardHand;
import blackjack.domain.participant2.Participant;

public class PlayerBetting {

    private final Participant player;
    private final int bettingAmount;

    public PlayerBetting(Participant player, int bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public Participant getPlayer() {
        return player;
    }

    public int profit(Participant dealer) {
        final CardHand playerHand = player.getHand();
        final CardHand dealerHand = dealer.getHand();

        return (int) playerHand.profit(dealerHand, bettingAmount);
    }

    @Override
    public String toString() {
        return "PlayerBetting{" +
                "player=" + player +
                ", bettingAmount=" + bettingAmount +
                '}';
    }
}
