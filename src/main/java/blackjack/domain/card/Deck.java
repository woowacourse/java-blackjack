package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static blackjack.domain.Game.WINNING_NUMBER;
import static java.util.Comparator.*;

public class Deck {

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
        deck.sort(comparing(Card::getScore, reverseOrder()));

        int score = 0;
        for (Card card : deck) {
            score += card.getScore(score);
        }

        return score;
    }

}
