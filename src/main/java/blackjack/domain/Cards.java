package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int MAX_SCORE = 21;
    private static final int ACE_BONUS_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (hasAce()) {
            return calculateAce(score);
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int calculateAce(int score) {
        if (score + ACE_BONUS_SCORE <= MAX_SCORE) {
            return score + ACE_BONUS_SCORE;
        }
        return score;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 남은 카드가 없습니다.");
        }
        return cards.remove(0);
    }
}
