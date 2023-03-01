package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACKJACK = 21;
    private static final int ACE_OFFSET = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        int score = sum();
        if (score >= BLACKJACK || hasNoAce()) {
            return score;
        }

        if (calculateAceAs11(score) <= BLACKJACK) {
            return calculateAceAs11(score);
        }

        return score;
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasNoAce() {
        return cards.stream().noneMatch(Card::isAce);
    }

    private int calculateAceAs11(int score) {
        return score + ACE_OFFSET;
    }
}
