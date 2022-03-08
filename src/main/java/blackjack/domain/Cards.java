package blackjack.domain;

import java.util.List;
import java.util.Objects;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        Objects.requireNonNull(cards, "[Error] 카드에는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    public int calculateScore() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getDefaultValue)
                .sum();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }
}
