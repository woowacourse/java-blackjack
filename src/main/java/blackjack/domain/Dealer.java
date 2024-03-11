package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer {
    public static final int INITIAL_CARDS_AMOUNT = 2;
    private static final String DEALER_NAME = "딜러";

    private final Player player;

    public Dealer() {
        this.player = Player.fromName(DEALER_NAME);
    }

    public void draw(Deck deck) {
        player.draw(deck);
    }

    public void initialDraw(Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_AMOUNT; i++) {
            draw(deck);
        }
    }

    public void drawUntilExceedMinimum(Deck deck) {
        while (player.shouldDealerDrawMore()) {
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

    public int getExtraCardsCount() {
        return player.getTotalCardsCount() - INITIAL_CARDS_AMOUNT;
    }
}
