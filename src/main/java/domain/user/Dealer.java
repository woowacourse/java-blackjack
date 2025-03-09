package domain.user;

import domain.TrumpCard;
import java.util.List;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_MAX_SCORE = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isAtLeastScore(DEALER_MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getFirstCard();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public List<TrumpCard> openAllCard() {
        return this.cardDeck.getAllCard();
    }
}
