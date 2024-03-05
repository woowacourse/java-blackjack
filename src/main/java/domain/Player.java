package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final Name name;
    private final List<Card> deck;

    public Player(Name name) {
        this.name = name;
        this.deck = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        deck.add(card);
    }

    public int calculateTotalScore() {
        if (deck.isEmpty()) {
            return 0;
        }
        return deck.stream()
                .map(Card::getRank)
                .mapToInt(Rank::getScore)
                .sum();
    }
}
