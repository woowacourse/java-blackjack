package domain;

import java.util.List;

public class Player implements Gambler{
    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName, Cards cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    @Override
    public void pickCard() {
        cards.addCard(Deck.pickCard());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
