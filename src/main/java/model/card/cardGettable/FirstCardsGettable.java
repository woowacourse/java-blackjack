package model.card.cardGettable;

import java.util.List;
import model.card.Card;

public class FirstCardsGettable implements CardsGettable {
    private static final String EMPTY_CARDS_MESSAGE = "보유한 카드가 없습니다.";

    @Override
    public List<Card> getCards(List<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CARDS_MESSAGE);
        }
        return List.of(cards.get(0));
    }
}
