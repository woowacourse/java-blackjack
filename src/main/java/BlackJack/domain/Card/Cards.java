package BlackJack.domain.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cards {
    public static final int BUST_LINE = 21;
    private static final int EXTRA_SCORE = 10;

    private final Set<Card> cards;

    public Cards(List<Card> deck) {
        this(new HashSet<>(deck));
    }

    public Cards(Set<Card> deck) {
        this.cards = new HashSet<>(deck);
    }

    public Set<Card> getCards() {
        return new HashSet<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();

        return addAceScore(score);
    }

    private int addAceScore(int score) {
        long countAce = cards.stream()
                .filter(Card::isAce)
                .count();

        for (int i = 0; i < countAce; i++) {
            score = getScore(score);
        }
        return score;
    }

    private int getScore(int score) {
        if (score + EXTRA_SCORE <= BUST_LINE) {
            score += EXTRA_SCORE;
        }
        return score;
    }
}
