package playerState;

import card.Card;
import card.Hand;
import gameState.GameStatus;
import java.util.List;

public abstract class PlayerStatus {

    protected final GameStatus gameStatus;
    protected final Hand hand;

    public PlayerStatus(GameStatus gameStatus, Hand hand) {
        this.gameStatus = gameStatus;
        this.hand = hand;
    }

    public abstract PlayerStatus addCard(Card card);

    public int getPoint() {
        return hand.getPoint();
    }

    public abstract PlayerStatus getFinalState(PlayerStatus dealerStatus);

    public int getBetAmount() {
        return gameStatus.getBettingAmount().getAmount();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
