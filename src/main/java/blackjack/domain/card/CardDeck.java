package blackjack.domain.card;

import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(final List<Card> cards) {
        checkCardDeckSize(cards);
        this.cards = cards;
    }

    private void checkCardDeckSize(final List<Card> cards) {
        if (52 != cards.stream()
                .distinct().count()) {
            throw new IllegalArgumentException("카드 덱은 중복되지 않은 52장으로만 생성할 수 있습니다.");
        }
    }
}
