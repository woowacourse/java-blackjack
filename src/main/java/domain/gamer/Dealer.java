package domain.gamer;

import domain.GameManager;
import domain.card.Card;
import domain.card.CardGroup;

import java.util.List;

public class Dealer {
    private final Gamer gamer;

    public Dealer(CardGroup cardGroup) {
        this.gamer = new Gamer(cardGroup);
    }

    public void receiveCard(Card card) {
        gamer.receiveCard(card);
    }

    public int calculateScore() {
        return gamer.calculateScore();
    }

    public boolean isLessThen(int score) {
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
