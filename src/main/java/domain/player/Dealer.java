package domain.player;

import domain.CardPossessor;

public class Dealer extends BlackJackPlayer {
    public Dealer(CardPossessor cards) {
        super(cards);
    }

    @Override
    public boolean canDrawMore() {
        return false;
    }
}
