package domain.participant;

import static domain.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import domain.card.Deck;
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

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    public void changeState() {
        if (gameState == GameState.HIT) {
            this.gameState = GameState.STAND;
        }
    }

    public void playTurn(Deck deck) {
        Card hitCard = deck.drawCard();
        hand.receiveCard(hitCard);
        if (isBust()) {
            changeState();
        }
    }

    public boolean isHit() {
        return this.gameState == GameState.HIT;
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
}
