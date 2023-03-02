package domain;

import java.util.List;

public class Dealer {

    private final PlayerCards playerCards;

    public Dealer() {
        this.playerCards = new PlayerCards();
    }

    public List<Card> getCards() {
        return playerCards.toList();
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }
}
