package blackjack.domain.card;

import blackjack.domain.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public int totalScore() {
        int totalScore = sum();
        for (long i = 0; i < countOfAce(); i++) {
            totalScore = checkAce(totalScore);
        }
        return totalScore;
    }

    private int checkAce(int totalScore) {
        int withAceScore = totalScore + CardNumber.acePlusNumber();
        if (Status.evaluateScore(withAceScore) != Status.BURST) {
            totalScore = withAceScore;
        }
        return totalScore;
    }

    private long countOfAce() {
        return deck.stream()
            .filter(Card::isAce)
            .count();
    }

    private int sum() {
        return deck.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(deck);
    }
}
