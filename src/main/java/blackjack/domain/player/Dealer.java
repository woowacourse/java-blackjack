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
        while (calculateScore().isLessThanDealerMinimumScore()) {
            draw(deck);
        }
    }

    public Score calculateScore() {
        return player.calculateScore();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public Player getPlayer() {
        return player;
    }

    public int getCardsCount() {
        Score score = calculateScore();
        return score.getCardsSize();
    }
}
