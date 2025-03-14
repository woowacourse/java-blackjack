package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;

import java.util.List;

public class Player {

    private final String name;
    private final Gamer gamer;

    public Player(String name, CardGroup cardGroup) {
        this.name = name;
        this.gamer = new Gamer(cardGroup);
    }

    public void receiveCard(Card card) {
        gamer.receiveCard(card);
    }

    public boolean isBlackJack() {
        return gamer.isBlackjack();
    }

    public boolean isBust() {
        return gamer.isBust();
    }

    public GameResult calculateGameResult(final int compareScore) {
        return gamer.calculateGameResult(compareScore);
    }

    public int calculateScore() {
        return gamer.calculateScore();
    }

    public List<Card> getCards() {
        return gamer.getCards();
    }

    public String getName() {
        return this.name;
    }
}
