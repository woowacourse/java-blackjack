package blackjack.card.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardFactory implements CardCreateStrategy {

    @Override
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>(Arrays.asList(Card.values()));
        Collections.shuffle(cards);
        return cards;
    }
}
