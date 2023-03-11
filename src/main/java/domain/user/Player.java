package domain.user;

import domain.card.Card;
import domain.game.Score;

public class Player {

    private final PlayerName playerName;
    private final Hand hand;

    public Player(String playerName, Hand hand) {
        this.playerName = new PlayerName(playerName);
        this.hand = hand;
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public Score sumHand() {
        return hand.score();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Hand getHand() {
        return hand;
    }
}
