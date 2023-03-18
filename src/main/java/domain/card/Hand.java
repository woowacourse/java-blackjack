package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {
    public static final Score maxDealerHit = new Score(16);

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public Score calculateScore() {
        Score sumScore = sum();
        if(sumScore.isOverMax()) {
            return sumScore.calculateAceAsOne(countOfAce());
        }
        return sumScore;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public boolean isBust() {
        return calculateScore().isOverMax();
    }

    public boolean isBlackjack() {
        return calculateScore().isMax() && hasTwoCards();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public boolean isDealerHit() {
        return calculateScore().isLessThanOrEqual(maxDealerHit);
    }

    private Score sum() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(new Score(0), Score::add);
    }

    private int countOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean hasTwoCards() {
        return cards.size() == 2;
    }
}
