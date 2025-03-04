package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participants {

    protected final List<Card> cards = new ArrayList<>();

    public void setUpCards(Card card1, Card card2) {
        cards.add(card1);
        cards.add(card2);
    }

    public void takeMore(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int number = cards.stream()
            .mapToInt(Card::getNumber)
            .sum();

        if (hasAce() && number + 10 <= 21) {
            return number + 10;
        }
        return number;
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    public List<Card> getCards() {
        return cards;
    }
}
