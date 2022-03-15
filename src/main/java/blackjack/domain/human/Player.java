package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.card.Cards;
import blackjack.util.Constants;

public final class Player extends Human {
    
    private Player(String name) {
        super(new Cards(), name);
    }
    
    public static Player of(String name) {
        return new Player(name);
    }
    
    public boolean isTwoCard() {
        return getCards().size() == Constants.INIT_CARD_NUMBER;
    }
    
    @Override
    public boolean isAbleToHit() {
        return cards.getPoint() < BLACKJACK_NUMBER;
    }
}
