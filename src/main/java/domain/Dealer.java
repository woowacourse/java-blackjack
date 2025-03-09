package domain;

import java.util.List;

public class Dealer extends Player {
    private static final int ADD_CARD_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> openInitialCards() {
        return getCards(1);
    }

    public boolean drawOneCardIfLowScore(Deck deck) {
        if (computeOptimalSum() <= ADD_CARD_THRESHOLD) {
            drawOneCard(deck);
            return true;
        }
        return false;
    }
}
