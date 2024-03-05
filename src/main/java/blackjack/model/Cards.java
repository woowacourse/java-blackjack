package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Deck> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(final List<Deck> cards) {
        this.cards = cards;
    }

    public Cards addCard(final Deck card) {
        cards.add(card);
        return new Cards(cards);
    }

    public int calculateScore() {
        int result = calculateBaseScore();
        int aceCount = countAce();

        if (aceCount == 1 && result > 21) {
            result -= aceCount * 10;
        }

        if (aceCount >= 2) {
            result -= aceCount * 10;
        }

        return result;
    }

    private int calculateBaseScore() {
        return cards.stream()
                .mapToInt(Deck::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Deck::isAce)
                .count();
    }

    public List<Deck> getCards() {
        return List.copyOf(cards);
    }
}
