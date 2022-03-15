package model.card.cardGettable;

import java.util.List;
import model.card.Card;

public class EveryCardsGettable implements CardsGettable {

    @Override
    public List<Card> getCards(List<Card> cards) {
        return cards;
    }
}
