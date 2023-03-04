package domain;

import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        this.cards = new Cards();
    }

    public void drawCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public String getName() {
        return playerName.getName();
    }

    public Score getScore() {
        return Score.from(cards.calculateScore());
    }

    public boolean isBustedPlayer(int sum) {
        return this.cards.isBusted(sum);
    }
}
