package domain.card;

import java.util.ArrayList;
import java.util.List;
import strategy.CardGenerator;

public class OneCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KING, Symbol.CLUB));

        return cards;
    }
}
