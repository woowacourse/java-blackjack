package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int sumScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        int aceCount = (int) cards.stream().filter(Card::isAce).count();

        while (totalScore > 21 && aceCount > 0) {
            totalScore -= 10;
            aceCount--;
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            String cardName = card.getCardName();
            cardNames.add(cardName);
        }
        return cardNames;
    }
}