package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    private final PlayerName playerName;
    private final List<Card> cards = new ArrayList<>();

    protected Participant(String name) {
        this.playerName = new PlayerName(name);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return playerName.getName();
    }

}
