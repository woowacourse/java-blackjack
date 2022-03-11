package model.cardGettable;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class EveryCardsGettable implements CardsGettable {

    @Override
    public List<Card> getCards(Cards cards) {
        return cards.getValue();
    }
}
