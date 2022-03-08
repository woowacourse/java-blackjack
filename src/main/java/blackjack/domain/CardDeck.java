package blackjack.domain;

import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> provideInitCards() {
        if (cards.size() < 2) {
            throw new IllegalStateException("[Error] 남은 카드가 2장 미만입니다.");
        }
        return null;
    }

    public Card provideCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[Error] 남은 카드가 없습니다.");
        }
        return null;
    }
}
