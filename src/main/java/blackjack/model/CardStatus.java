package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class CardStatus {

    private final List<Card> cards;
    private int totalScore;
    private boolean isBlackjack;
    private boolean isBust;

    public CardStatus() {
        this.cards = new ArrayList<>();
        this.totalScore = 0;
        this.isBlackjack = false;
        this.isBust = false;
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
}
