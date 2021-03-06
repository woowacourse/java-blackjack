package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.Game.WINNING_NUMBER;

public class Deck {

    private static final int ACE_EXCEPTION_NUMBER = 10;

    private final List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public boolean contains(Card card) {
        return deck.contains(card);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(deck);
    }

    public int getScore() {
        int scoreExceptAce = deck.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();

        return deck.stream()
                .filter(Card::isAce)
                .mapToInt(card -> scoreCalculate(scoreExceptAce, card))
                .sum() + scoreExceptAce;
    }

    private int scoreCalculate(int scoreExceptAce, Card card) {
        if (scoreExceptAce + card.getScore() + ACE_EXCEPTION_NUMBER > WINNING_NUMBER) {
            return card.getScore();
        }

        return card.getScore() + ACE_EXCEPTION_NUMBER;
    }
}
