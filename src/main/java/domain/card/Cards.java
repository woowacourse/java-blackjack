package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int INIT_CARD_SIZE = 2;
    private static final int ACE_SCORE = 10;
    private static final int INIT_SIZE = 2;
    protected static final int MAX_SCORE = 21;

    protected final List<Card> cards;

    public Cards(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<Card> cards) {
        if (cards.size() != INIT_CARD_SIZE) {
            throw new IllegalArgumentException("처음 지급받는 카드는 두 장이어야 합니다.");
        }
    }

    public boolean isBurst() {
        return bestSum() > MAX_SCORE;
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isBlackjack() {
        return cards.size() == INIT_SIZE && bestSum() == MAX_SCORE;
    }

    public boolean isGreaterThan(Cards other) {
        return this.bestSum() > other.bestSum();
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int bestSum() {
        long countAce = countAce();
        int sum = sum();
        while (sum + ACE_SCORE <= MAX_SCORE && countAce > 0) {
            sum += ACE_SCORE;
            countAce--;
        }
        return sum;
    }

    public int sum() {
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
}
