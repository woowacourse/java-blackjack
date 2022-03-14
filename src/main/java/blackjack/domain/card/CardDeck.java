package blackjack.domain.card;

import java.util.List;

public class CardDeck {

    private static final String NO_CARD_ERROR_MESSAGE = "[ERROR] 남은 카드가 존재하지 않습니다.";
    private static final int LAST_INDEX = 1;

    //TODO: Stack으로 구현
    private List<Card> cards;

    private CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck generate(List<Card> cards) {
        return new CardDeck(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_ERROR_MESSAGE);
        }
        return cards.remove(cards.size() - LAST_INDEX);
    }
}
