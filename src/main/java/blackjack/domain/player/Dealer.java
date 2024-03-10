package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";

    private final Player player;

    public Dealer() {
        this.player = new Player(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void drawUntilExceedMinimum(Deck deck) {
        while (getScore().isLessThanDealerMinimumScore()) {
            draw(deck);
        }
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public Score getScore() {
        return player.getScore();
    }

    public Player getPlayer() {
        return player;
    }

    public int getCardsCount() {
        return player.getTotalCardsCount();
    }
}
