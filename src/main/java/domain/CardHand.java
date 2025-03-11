package domain;

import domain.deck.Card;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CardHand {
    private final Set<Card> cards;

    public CardHand(Set<Card> cards) {
        this.cards = new LinkedHashSet<>(cards);
    }

    public void add(Card newCard) {
        cards.add(newCard);
    }

    public GameScore calculateScore() {
        GameScore totalScore = new GameScore(cards.stream()
                .mapToInt(Card::score)
                .sum());
        if (hasAce()) {
            return totalScore.withAce();
        }
        return totalScore;
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

    public boolean isBlackJack() {
        return calculateScore().isBlackJack();
    }

    public boolean doesDealerNeedCard() {
        return calculateScore().doesDealerNeedCard();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
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
