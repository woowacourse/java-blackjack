package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        cards = new ArrayList<>(cards);
        validateNull(cards);
        validateDistinct(cards);
        this.cards = cards;
    }

    private void validateNull(List<Card> cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null일 수 없습니다.");
    }

    private void validateDistinct(List<Card> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("[ERROR] 카드는 중복될 수 없습니다.");
        }
    }

    public Card draw() {
        validateCards();
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    private void validateCards() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 더 이상 뽑을 수 있는 카드가 없습니다.");
        }
    }

    public List<Card> drawDouble() {
        return new ArrayList<>(List.of(draw(), draw()));
    }
}
