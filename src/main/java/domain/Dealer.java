package domain;

public class Dealer extends Player {
    private static final int ADD_CARD_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public void openInitialCards() {
        openCards(1);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public boolean drawOneCardIfLowScore(Deck deck) {
        if (computeOptimalSum() <= ADD_CARD_THRESHOLD) {
            drawOneCard(deck);
            return true;
        }
        return false;
    }
}
