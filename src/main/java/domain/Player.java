package domain;

import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        this.cards = new Cards();
    }

    public void initialPick() {
        pickCard();
        pickCard();
    }

    public void pickCard() {
        cards.addCard(Deck.pickCard());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return playerName.getName();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBustedGambler(int sum) {
        return this.cards.isBusted(sum);
    }
}
