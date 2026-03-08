package domain.card;

import domain.score.Score;
import java.util.Collections;
import java.util.List;
import util.ErrorMessage;

public class Hand {
    private static final int MIN_SIZE = 2;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        validateCardsSize(cards);
        this.cards = cards;
    }

    private void validateCardsSize(List<Card> cards) {
        if (cards.size() != MIN_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.HANDS_CARDS_SIZE.getMessage());
        }
    }

    public Score getScore() {
        int totalScore = cards.stream()
                .map(Card::getScore)
                .reduce(Integer::sum)
                .orElse(0);

        return new Score(totalScore, hasAce(cards));
    }

    public void add(Card card) {
        cards.add(card);
    }

    private boolean hasAce(List<Card> hands) {
        return hands.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
