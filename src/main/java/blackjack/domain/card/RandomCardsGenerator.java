package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> generate() {
        LinkedList<Card> cards = new LinkedList<>(Card.VALUES);
        Collections.shuffle(cards);

        return cards;
    }
}
