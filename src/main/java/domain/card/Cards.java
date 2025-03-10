package domain.card;

import static domain.GameManager.*;

import domain.GameManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards of() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getMinScore)
                .sum();

        List<Card> scoreGapCards = cards.stream()
                .filter(Card::hasScoreGap)
                .toList();

        return scoreGapCards.stream()
                .reduce(sum, (acc, card) -> addScoreIfPossible(acc, card.getScoreGap()), Integer::sum);
    }

    private int addScoreIfPossible(int currentSum, int scoreGap) {
        if (currentSum + scoreGap > BLACKJACK_SCORE) {
            return currentSum;
        }
        return currentSum + scoreGap;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
