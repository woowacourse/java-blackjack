package domain.gameplaying;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_WEIGHT = 10;

    private final BlackJackDeck deck;
    private final List<Card> cards;

    Hand(BlackJackDeck deck, List<Card> cards) {
        this.deck = deck;
        this.cards = new ArrayList<>(cards);
    }

    static Hand with(BlackJackDeck deck) {
        return new Hand(deck, new ArrayList<>());
    }

    void drawCard() {
        cards.add(deck.draw());
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * ACE_WEIGHT;
        }
        return score > BUST_THRESHOLD;
    }

    int scoreSum() {
        int total = rawScoreSum();
        if (isExceededBustNumber(total)) {
            total -= aceCount() * ACE_WEIGHT;
        }
        return total;
    }

    private int rawScoreSum() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isExceededBustNumber(int total) {
        return total > BUST_THRESHOLD;
    }
}
