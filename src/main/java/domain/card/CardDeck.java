package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(ShuffleStrategy generator) {
        this.cards = new ArrayList<>(Arrays.asList(Card.values()));
        generator.shuffle(cards);
    }

    public Card deal() {
        return cards.removeFirst();
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(cards);
    }
}

