package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Participator {
    protected final Cards cards;
    private final PlayerName playerName;

    public Participator(PlayerName playerName) {
        this.cards = new Cards(new ArrayList<>());
        this.playerName = playerName;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getValue());
    }

    public boolean canReceiveCard() {
        return cards.canReceiveCard();
    }
}
