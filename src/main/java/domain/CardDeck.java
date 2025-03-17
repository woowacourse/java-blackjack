package domain;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    public static final int MAX_SCORE = 21;
    private static final int ACE_MAX = 11;
    private static final int ACE_MIN = 1;
    private static final int BLACK_SIZE = 2;

    private final List<TrumpCard> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public void addTrumpCard(final TrumpCard card) {
        cards.add(card);
    }

    public List<TrumpCard> getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 비었습니다.");
        }
        return List.of(cards.getFirst());
    }

    public List<TrumpCard> getAllCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드가 비었습니다.");
        }
        return new ArrayList<>(cards);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(TrumpCard::isAce);
    }

    public boolean isImpossibleDraw(final int maxScore) {
        int sum = calculateScore();
        return sum >= maxScore;
    }

    public boolean checkOverScore() {
        int sum = calculateScore();
        return sum > MAX_SCORE;
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(TrumpCard::getCardNumberValue)
                .sum();
        if (hasAce() && sum <= ACE_MAX) {
            sum += (ACE_MAX - ACE_MIN);
        }
        return sum;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACK_SIZE && calculateScore() == MAX_SCORE;
    }
}
