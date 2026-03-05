package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class CardStatus {

    private final List<Card> cards;
    private int totalScore;
    private boolean blackjack;

    public CardStatus() {
        this.cards = new ArrayList<>();
        this.totalScore = 0;
        this.blackjack = false;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isBlackjack() {
        return blackjack;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void markBlackjack() {
        this.blackjack = true;
    }
}
