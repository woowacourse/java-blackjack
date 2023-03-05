package domain;

import java.util.List;

public final class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    public static final int CARD_RENEWAL_COUNT = 1;
    private static final int DEALER_STAY_NUMBER = 17;


    private Dealer(final String name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME, new Cards());
    }

    public boolean dealerIsHit(){
        return cards.calculateScore() < DEALER_STAY_NUMBER;
    }

    @Override
    public boolean isInPlaying(boolean isHit){
        return isHit;
    }

    @Override
    public List<Card> revealCards() {
        return cards.getCards().subList(0, CARD_RENEWAL_COUNT);
    }
}
