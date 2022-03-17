package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import blackjack.domain.state.CardHand;

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

    public int getProfit(Participant dealer) {
        final double bettingYield = getBettingYield(dealer);
        return (int) (bettingYield * bettingAmount);
    }

    private double getBettingYield(Participant dealer) {
        final CardHand playerHand = player.getCardHand();
        final CardHand dealerHand = dealer.getCardHand();

        return playerHand.getBettingYieldVersus(dealerHand);
    }

    @Override
    public String toString() {
        return "PlayerBetting{" +
                "player=" + player +
                ", bettingAmount=" + bettingAmount +
                '}';
    }
}
