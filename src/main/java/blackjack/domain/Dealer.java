package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_SCORE = 17;

    private final Player player;

    public Dealer() {
        this.player = new Player(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void drawUntilExceedMinimum(Deck deck) {
        while (getScore() < MINIMUM_SCORE) {
            draw(deck);
        }
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public int getScore() {
        return player.getScore();
    }

    public boolean isBusted() {
        return player.isBusted();
    }
}
