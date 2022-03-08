package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    List<Card> cards = new LinkedList<>(Arrays.asList(Card.values()));

    public int size() {
        return cards.size();
    }

    public Card draw() {
        Collections.shuffle(cards);
        Card card = cards.get(cards.size() - 1);
        cards.remove(card);
        return card;
    }

    public List<Card> initialDraw(){
        Collections.shuffle(cards);
        List<Card> result = cards.subList(0,2);
        cards = cards.subList(2,size());
        return result;
    }
}
