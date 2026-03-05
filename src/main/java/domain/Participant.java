package domain;

import domain.card.Card;
import java.util.List;

public class Participant {

    private final Name name;
    private final Hand hand;
    private GameState gameState;

    public Participant(Name name, Hand hand) {
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

    public List<Card> getCards() {
        return hand.getCards();
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
