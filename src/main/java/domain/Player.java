package domain;

import java.util.List;

public class Player {
    private final Name name;
    private final PlayerCards playerCards;

    public Player(String name) {
        this.name = new Name(name);
        this.playerCards = new PlayerCards();
    }

    public String getName() {
        return name.getValue();
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public List<Card> getCards() {
        return playerCards.toList();
    }
}
