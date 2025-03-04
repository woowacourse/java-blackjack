package domain;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private final String name;
    private final Set<Card> cards = new HashSet<>();

    public Player(String name) {
        this.name = name;
    }

    public void setUpCard(Card card1, Card card2) {
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

    public Set<Card> getCards() {
        return cards;
    }
}
