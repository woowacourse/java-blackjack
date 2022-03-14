package utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
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
