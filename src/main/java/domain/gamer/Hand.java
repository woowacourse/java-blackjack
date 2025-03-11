package domain.gamer;

import static domain.BlackJackConstants.BUST_NUMBER;

import domain.deck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int getSumOfRank() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return getSumOfRank() > BUST_NUMBER;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
