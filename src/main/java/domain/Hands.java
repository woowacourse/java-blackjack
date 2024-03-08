package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hands {

    private static final int BLACK_JACK = 21;
    public static final int EXTRA_ACE_VALUE = 10;

    private final List<Card> cards;

    public Hands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hands createEmptyPacket() {
        return new Hands(new ArrayList<>());
    }

    public int sum() {
        int total = cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();

        return calculateTotalByAce(total);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return sum() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return sum() == BLACK_JACK;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int size() {
        return cards.size();
    }

    public List<String> getCards() {
        return cards.stream()
                .map(Card::toString)
                .toList();
    }

    public Result calculateResult(final Hands target) {
        if (!this.isBust() && target.isBust()) {
            return Result.WIN;
        }
        if (this.sum() == target.sum() && this.size() == target.size() && !this.isBust()) {
            return Result.TIE;
        }
        if (this.sum() >= target.sum() && this.sum() <= BLACK_JACK) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private int calculateTotalByAce(final int total) {
        if (hasAce() && total + EXTRA_ACE_VALUE <= BLACK_JACK) {
            return total + EXTRA_ACE_VALUE;
        }

        return total;
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }

        if (!(target instanceof Hands hands)) {
            return false;
        }

        return Objects.equals(cards, hands.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
