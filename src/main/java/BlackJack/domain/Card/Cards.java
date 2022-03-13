package BlackJack.domain.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {
    public static final int BUST_LINE = 21;
    private static final int EXTRA_SCORE = 10;

    private final Set<Card> deck;

    public Cards(List<Card> deck) {
        this(new HashSet<>(deck));
    }

    public Cards(Set<Card> deck) {
        this.deck = new HashSet<>(deck);
    }

    public Set<Card> getDeck() {
        return new HashSet<>(deck);
    }

    public void add(Card card) {
        deck.add(card);
    }

    public int calculateScore() {
        int score = deck.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        return addAceScore(score);
    }

    private int addAceScore(int score) {
        long countAce = deck.stream()
                .filter(Card::containsAce)
                .count();

        for (int i = 0; i < countAce; i++) {
            if (score + EXTRA_SCORE <= BUST_LINE) {
                score += EXTRA_SCORE;
            }
        }
        return score;
    }
}
