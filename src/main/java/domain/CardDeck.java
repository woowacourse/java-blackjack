package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<Card> deck = new ArrayList<>();

    public void setUpCards(Card card1, Card card2) {
        deck.add(card1);
        deck.add(card2);
    }

    public void takeMore(Card card) {
        deck.add(card);
    }

    public int calculateScore() {
        int number = deck.stream()
            .mapToInt(Card::getNumber)
            .sum();

        if (hasAce() && number + 10 <= 21) {
            return number + 10;
        }
        return number;
    }

    private boolean hasAce() {
        return deck.stream()
            .anyMatch(card -> card.getRank() == Rank.ACE);
    }

    public List<Card> getCards() {
        return List.copyOf(deck);
    }
}
