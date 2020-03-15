package domain.card;

import java.util.List;
import java.util.Objects;

public class PlayingCards {
    private static final int ACE_BONUS = 10;
    private static final int BLACK_JACK = 21;
    private List<Card> cards;

    public PlayingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int result = cards.stream()
                .mapToInt(Card::getValue)
                .sum();
        for (Card card : cards) {
            if (canAddAceBonus(result, card)) {
                result += ACE_BONUS;
            }
        }
        return result;
    }

    private boolean canAddAceBonus(int result, Card card) {
        return card.isAce() && result + ACE_BONUS <= BLACK_JACK;
    }

    public int size() {
        return cards.size();
    }

    public boolean isBust() {
        return BLACK_JACK < calculateScore();
    }

    public boolean isNotBust() {
        return calculateScore() <= BLACK_JACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCards that = (PlayingCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
