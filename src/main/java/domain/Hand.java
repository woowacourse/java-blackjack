package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<TrumpCard> cards;

    public Hand(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
        validateNotNull(cards);
        validateNotDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("손패는 카드를 가지고 있어야합니다.");
        }
    }

    private void validateNotDuplicate(List<TrumpCard> cards) {
        int distinctCount = (int) cards.stream().distinct().count();
        int originalCount = cards.size();
        if (distinctCount != originalCount) {
            throw new IllegalArgumentException("손패에 중복된 카드가 있습니다.");
        }
    }

    public static Hand of(TrumpCard firstCard, TrumpCard secondCard) {
        return new Hand(List.of(firstCard, secondCard));
    }

    public void addCard(TrumpCard card) {
        validateNotDuplicate(card);
        cards.add(card);
    }

    private void validateNotDuplicate(TrumpCard card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("이미 손패에 있는 카드입니다.");
        }
    }

    public int calculateTotalScore() {
        int totalScore = getTotalScore(cards);
        int aceCount = getAceCount(cards);
        int bonusScore = calculateAceBonusScore(totalScore, aceCount);
        return totalScore + bonusScore;
    }

    private int getTotalScore(List<TrumpCard> cards) {
        return cards.stream().mapToInt(c -> c.getRank().getValue()).sum();
    }

    private int getAceCount(List<TrumpCard> cards) {
        return (int) cards.stream().filter(c -> c.getRank() == Rank.ACE).count();
    }

    private int calculateAceBonusScore(int totalScore, int aceCount) {
        if (aceCount > 0 && totalScore + 10 <= 21) {
            return 10;
        }
        return 0;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && calculateTotalScore() == 21;
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
    }

    public List<TrumpCard> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
