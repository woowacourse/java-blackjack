package domain.card;

import strategy.CardGenerator;

import java.util.ArrayList;
import java.util.List;

public class OneCardGenerator implements CardGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.KING, Symbol.CLUB));

        return List.copyOf(cards);
    }
}
