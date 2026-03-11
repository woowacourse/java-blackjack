package model;

import exception.GameException;
import java.util.ArrayList;
import java.util.List;
import dto.Card;

public class BlackJackDeck {
    private static final String NO_CARD_IN_DECK = "카드 뭉치에 더 이상 남아있는 카드가 없습니다.";

    private final List<Card> cards;

    public BlackJackDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new GameException(NO_CARD_IN_DECK);
        }
        return cards.removeFirst();
    }
}
