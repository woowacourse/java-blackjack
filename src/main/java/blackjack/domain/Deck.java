package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int MAX_NUM_CARD = 52;
    private static final int FIRST = 0;

    private final List<Card> deck = new ArrayList<>(MAX_NUM_CARD);

    public Deck() {
        for (CardPattern cardPattern : CardPattern.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                deck.add(Card.valueOf(cardPattern, cardNumber));
            }
        }
        Collections.shuffle(deck);
    }

    public Card dealCard() {
        return deck.remove(FIRST);
    }

    public Card choiceCard(int index) {
        return deck.get(index);
    }

    public int getSize() {
        return deck.size();
    }
}
