package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final int BUST_LIMIT = 21;

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

    public boolean isBust() {
        return calculateMyCardSum() > BUST_LIMIT;
    }

    private int calculateMyCardSum() {
        return myCards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }
}
