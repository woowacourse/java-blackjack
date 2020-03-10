package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public static final int INITIAL_CARD_NUMBER = 2;
    private List<Card> cards = new ArrayList<>();

    public void initialDistribution(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            cards.add(cardDeck.draw());
        }
    }
}
