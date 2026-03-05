package domain;

import domain.card.Card;

public class Player {

    private final Name name;
    private final Hand hand;
    private GameState gameState;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
        this.gameState = GameState.HIT;
    }

    public void receiveCard(Card card) {
        hand.receiveCard(card);
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        return hand.calculate();
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public void changeState() {
        if (gameState == GameState.HIT) {
            this.gameState = GameState.STAND;
            return;
        }
        this.gameState = GameState.HIT;
    }
}
