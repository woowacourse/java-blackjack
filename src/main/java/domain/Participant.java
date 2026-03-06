package domain;

import static domain.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Participant {

    private static final int INIT_CARD_SIZE = 2;

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


    public void initHand(Deck deck) {
        for (int size = 0; size < INIT_CARD_SIZE; size++) {
            Card card = deck.drawCard();
            hand.receiveCard(card);
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
