package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final int MINIMUM_SCORE = 17;

    private final Player player;

    public Dealer() {
        this.player = new Player();
    }

    public void draw(Deck deck) {
        while (getScore() < MINIMUM_SCORE) {
            player.draw(deck);
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
