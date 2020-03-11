package domain.player;

import domain.card.HandCards;

public class Dealer extends BlackJackPlayer {
    public Dealer() {
        super("딜러", new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return false;
    }
}
