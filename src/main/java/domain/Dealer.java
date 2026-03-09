package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    private final Deck deck;

    public Dealer(Deck deck) {
        super(new ArrayList<>(List.of(deck.draw(), deck.draw())));
        this.deck = deck;
    }

    public Card dealCard() {
        return deck.draw();
    }

    public boolean isReceiveCard() {
        return calculateTotalScore() <= 16;
    }
}
