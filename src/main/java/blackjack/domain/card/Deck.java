package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

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
        List<Card> deckForCalculate = new ArrayList<>(deck);
        deckForCalculate.sort(comparing(Card::getAccumulateScore, reverseOrder()));

        int score = 0;
        for (Card card : deckForCalculate) {
            score += card.getAccumulateScore(score);
        }

        return score;
    }

}
