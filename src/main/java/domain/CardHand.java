package domain;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int MAX_SCORE = 21;
    private static final int ACE_MAX = 11;
    private static final int ACE_MIN = 1;

    private final List<TrumpCard> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public void addTrumpCard(TrumpCard card) {
        cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }

    public List<TrumpCard> getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드 덱을 모두 사용했습니다.");
        }
        return List.of(cards.getFirst());
    }

    public List<TrumpCard> getAllCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("카드 덱을 모두 사용했습니다.");
        }
        return new ArrayList<>(cards);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch((card) -> card.getCardNumberValue() == CardRank.ACE.getPoint());
    }

    public boolean isAtLeastScore(int maxScore) {
        int score = calculateScore();
        return score >= maxScore;
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
        return cards.size() == BLACKJACK_CARD_COUNT && calculateScore() == MAX_SCORE;
    }
}
