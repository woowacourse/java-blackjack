package domain.card;

import domain.game.Score;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private static final int BLACKJACK_CARD_COUNT = 2;

    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new LinkedHashSet<>(cards);
    }

    public int calculateScore() {
        return Score.calculate(cards).value();
    }

    public boolean isBust() {
        return Score.isBust(cards);
    }

    public boolean isBlackJack() {
        return Score.isBlackJack(cards) && cards.size() == BLACKJACK_CARD_COUNT;
    }

    public boolean doesDealerNeedCard() {
        return Score.doesDealerNeedCard(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardHand cardHand = (CardHand) o;
        return Objects.equals(cards, cardHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
