package model;

import java.util.ArrayList;
import java.util.List;

public class Participator {
    protected final List<Card> cards;
    private final PlayerName playerName;

    public Participator(PlayerName playerName) {
        this.cards = new ArrayList<>();
        this.playerName = playerName;
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
