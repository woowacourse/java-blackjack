package blackjack.domain.card;

import java.util.*;

import static blackjack.domain.state.BlackJack.BLACKJACK_NUMBER;

public class Cards {
    private static final String DUPLICATE_CARD_ERROR_MESSAGE = "중복된 카드는 존재할 수 없습니다.";
    private static final int DEALER_DRAW_CONDITION = 16;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        if (cards.contains(card)) {
            throw new IllegalStateException(DUPLICATE_CARD_ERROR_MESSAGE);
        }
        cards.add(card);
    }

    public boolean isBlackJack() {
        return calculateScore() == BLACKJACK_NUMBER;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_NUMBER;
    }

    public boolean isStay() {
        return cards.size() > BLACKJACK_CARD_SIZE && isBlackJack();
    }

    public boolean isWin(Cards cards) {
        return calculateScore() > cards.calculateScore();
    }

    public boolean isDraw(Cards cards) {
        return calculateScore() == cards.calculateScore();
    }

    public boolean lessThanSixteen() {
        return calculateScore() <= DEALER_DRAW_CONDITION;
    }

    public int calculateScore() {
        int score = calculateTotalScore();
        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();
        return Denomination.plusRemainAceScore(score, aceCount);
    }

    public int calculateTotalScore() {
        int score = 0;
        for (Card card : cards) {
            score = card.addScore(score);
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
