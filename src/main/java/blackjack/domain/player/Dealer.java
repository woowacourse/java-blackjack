package blackjack.domain.player;

import blackjack.domain.card.Cards;

public class Dealer extends Player {

    public static final int DEALER_SCORE_PIVOT = 16;

    public Dealer(Cards cards) {
        super(cards);
    }
    
    public boolean isEnoughScore(){
        return getScore() > DEALER_SCORE_PIVOT;
    }
}
