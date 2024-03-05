package player;

import card.Deck;

public class Dealer extends Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    @Override
    public void drawCard(Deck deck) {
        while (hasDrawableScore()) {
            super.drawCard(deck);
        }
    }

    private boolean hasDrawableScore() {
        return calculateScore() <= MAX_DRAWABLE_SCORE;
    }
}
