package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateSize(final List<Card> cards) {
        if (cards.size() < 2) {
            throw new IllegalArgumentException("카드는 두 장 이상이어야 합니다.");
        }
    }

    public Cards addCard(final Card card) {
        cards.add(card);
        return new Cards(cards);
    }

    public int calculateScore() {
        int result = calculateBaseScore();
        int aceCount = countAce();

        if (aceCount == 1 && result > 21) {
            result -= aceCount * 10;
        }

        if (aceCount >= 2) {
            result -= aceCount * 10;
        }

        return result;
    }

    private int calculateBaseScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }
}
