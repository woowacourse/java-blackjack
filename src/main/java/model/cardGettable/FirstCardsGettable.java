package model.cardGettable;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class FirstCardsGettable implements CardsGettable {

    @Override
    public List<Card> getCards(Cards cards) {
        return List.of(cards.getFirstCard());
    }
}
