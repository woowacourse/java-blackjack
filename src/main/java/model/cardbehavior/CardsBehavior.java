package model.cardbehavior;

import java.util.List;
import model.Card;
import model.Cards;

public interface CardsBehavior {

    List<Card> getCards(Cards cards);
}
