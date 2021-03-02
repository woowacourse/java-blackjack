package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> myCards;

    public Player(final String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        myCards.add(card);
    }

    public int getCardCount() {
        return myCards.size();
    }
}
