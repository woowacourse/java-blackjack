package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(CardsGenerator generator) {
        this.cards = generator.generate();
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
