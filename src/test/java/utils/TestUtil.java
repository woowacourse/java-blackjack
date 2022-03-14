package utils;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static Cards getCards(Number... arguments) {
        List<Card> list = new ArrayList<>();
        for (Number number : arguments) {
            list.add(new Card(number, Suit.CLOVER));
        }
        return new Cards(list);
    }

}
