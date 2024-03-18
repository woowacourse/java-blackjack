package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final int INIT_CARD_SIZE = 2;
    private static final int ACE_SCORE = 10;
    private static final int INIT_SIZE = 2;
    protected static final int MAX_SCORE = 21;

    protected final List<Card> cards;

    public Participant(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("처음 지급받는 카드는 두 장이어야 합니다.");
        }
    }

    protected abstract boolean canDraw();

    public boolean isBurst() {
        return bestSumOfCardScore() > MAX_SCORE;
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBlackjack() {
        return cards.size() == INIT_SIZE && bestSumOfCardScore() == MAX_SCORE;
    }

    public boolean isGreaterThan(Participant other) {
        return this.bestSumOfCardScore() > other.bestSumOfCardScore();
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int bestSumOfCardScore() {
        long countAce = countAce();
        int sum = sumOfCardScore();
        while (sum + ACE_SCORE <= MAX_SCORE && countAce > 0) {
            sum += ACE_SCORE;
            countAce--;
        }
        return sum;
    }

    public int sumOfCardScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::asString)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant participant1 = (Participant) o;
        return Objects.equals(cards, participant1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards=" + cards +
                '}';
    }

}
