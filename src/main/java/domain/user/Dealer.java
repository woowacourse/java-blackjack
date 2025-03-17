package domain.user;

import domain.TrumpCard;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_MAX_SCORE = 16;
    private static final String DEALER_FIX_NAME = "딜러";

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isImpossibleDraw(DEALER_MAX_SCORE);
    }
    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getFirstCard();
    }

    @Override
    public List<TrumpCard> openAllCard() {
        return this.cardDeck.getAllCard();
    }

    @Override
    public String getName() {
        return DEALER_FIX_NAME;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
