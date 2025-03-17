package user;

import card.Card;
import card.CardRank;
import java.util.ArrayList;
import java.util.List;

public class CardHand {
    public static final int BLACKJACK_CARD_COUNT = 2;
    public static final int MAX_SCORE = 21;
    private static final int ACE_MAX = 11;
    private static final int ACE_MIN = 1;

    private final List<Card> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getFirstCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("갖고 있는 카드가 없습니다.");
        }
        return List.of(cards.getFirst());
    }

    public List<Card> getAllCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("갖고 있는 카드가 없습니다.");
        }
        return new ArrayList<>(cards);
    }

    private boolean hasAce() {
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
                .mapToInt(Card::getCardNumberValue)
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
