package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "딜러";

    private final Player player;

    public Dealer() {
        this.player = Player.fromName(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void draw(Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            draw(deck);
        }
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
