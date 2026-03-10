package model;

import constant.CardErrorCode;
import exception.GameException;
import java.util.ArrayList;
import java.util.List;
import dto.Card;

public class BlackJackDeck {
    private final List<Card> cards;

    public BlackJackDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new GameException(CardErrorCode.NO_CARD_IN_DECK);
        }
        return cards.removeFirst();
    }
}
