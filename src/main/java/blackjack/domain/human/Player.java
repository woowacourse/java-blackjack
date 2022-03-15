package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.card.group.Cards;
import blackjack.util.Constants;

public final class Player extends Human {
    
    private Player(String name) {
        super(new Cards(), name);
    }
    
    public static Player from(String name) {
        return new Player(name);
    }
    
    public boolean isTwoCard() {
        return getCards().size() == Constants.INIT_CARD_NUMBER;
    }
    
    public boolean isWinner(Dealer dealer) {
        return dealer.getPoint() < getPoint();
    }
    
    public boolean isDraw(Dealer dealer) {
        return dealer.getPoint() == getPoint();
    }
    
    @Override
    public boolean isAbleToHit() {
        return getPoint() < BLACKJACK_NUMBER;
    }
}
