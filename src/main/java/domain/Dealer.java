package domain;

import java.util.List;

public class Dealer extends Player {
    private static final int ADD_CARD_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> openInitialCards() {
        return getCards().getCards(1);
    }

    public boolean addCardIfLowScore(Deck deck) {
        if (getCards().calculateOptimalSum() <= ADD_CARD_THRESHOLD) {
            addCard(deck);
            return true;
        }
        return false;
    }
}
