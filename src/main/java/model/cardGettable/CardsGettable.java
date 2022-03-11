package model.cardGettable;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public interface CardsGettable {

    List<Card> getCards(Cards cards);
}
