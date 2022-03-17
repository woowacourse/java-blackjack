package blackjack.domain.betting;

import blackjack.domain.participant.Participant;

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

    public int getBettingAmount() {
        return bettingAmount;
    }

    @Override
    public String toString() {
        return "PlayerBetting{" +
                "player=" + player +
                ", bettingAmount=" + bettingAmount +
                '}';
    }
}
