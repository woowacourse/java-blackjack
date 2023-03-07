package blackjack.domain.card;

import java.util.Queue;

public class Deck {

    private static final int NUMBER_OF_CARDS_IN_DECK = 52;

    private final Queue<Card> cards;

    Deck(final Queue<Card> cards) {
        validateCards(cards);
        this.cards = cards;
    }

    private void validateCards(final Queue<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("카드에 null 이 들어왔습니다");
        }
        if (cards.size() != NUMBER_OF_CARDS_IN_DECK) {
            throw new IllegalArgumentException(
                    "카드 숫자는 " + NUMBER_OF_CARDS_IN_DECK + "장이어야 합니다 현재 :" + cards.size() + "장");
        }
    }

    public Card removeCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 없습니다");
        }
        return cards.remove();
    }
}
