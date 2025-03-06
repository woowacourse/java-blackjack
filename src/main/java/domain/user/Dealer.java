package domain.user;

import domain.TrumpCard;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_MAX_SCORE = 16;

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean isImpossibleDraw() {
        return cardDeck.isImpossibleDraw(DEALER_MAX_SCORE);
    }

    @Override
    public List<TrumpCard> openCard() {
        return this.cardDeck.getFirstCard();
    }

    public List<TrumpCard> openAllCard() {
        return this.cardDeck.getAllCard();
    }
}
