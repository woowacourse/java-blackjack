package model.cardbehavior;

import java.util.List;
import model.Card;
import model.Cards;
import model.cardbehavior.CardsBehavior;

public class EveryCardsBehavior implements CardsBehavior {

    @Override
    public List<Card> getCards(Cards cards) {
        return cards.getValue();
    }
}
