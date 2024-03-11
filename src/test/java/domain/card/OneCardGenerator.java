package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import strategy.CardGenerator;

public class OneCardGenerator implements CardGenerator {

    @Override
    public Queue<Card> generate() {
        return new LinkedList<>(List.of(new Card(Rank.KING, Symbol.CLUB)));
    }
}
