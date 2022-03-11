package model.cardbehavior;

import java.util.List;
import model.Card;
import model.Cards;

public class FirstCardsBehavior implements CardsBehavior {

    @Override
    public List<Card> getCards(Cards cards) {
        return List.of(cards.getFirstCard());
    }
}
