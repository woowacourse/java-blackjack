package domain.gamer;

import domain.GameManager;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

public class Dealer {
    private final int DEALER_HIT_ROLE = 16;
    private final Gamer gamer;

    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        this.gamer = new Gamer(cardGroup, cardGenerator);
    }

    public int calculateScore() {
        return gamer.calculateScore();
    }

    public void receiveCard(int count) {
        gamer.receiveCard(count);
    }

    public void hitCardUntilStand() {
        while (isLessThen(DEALER_HIT_ROLE)) {
            gamer.receiveCard();
        }
    }

    private boolean isLessThen(int score) {
        return gamer.calculateScore() <= score;
    }

    public boolean isBust() {
        return gamer.isBust();
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public int getReceivedCardCount() {
        return gamer.getCards().size() - GameManager.START_RECEIVE_CARD;
    }
}
