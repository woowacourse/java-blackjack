package domain.gamer;

import domain.GameManager;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;

import java.util.List;

public class Dealer {
    private static final String DEALER_NAME = "NEO";
    private final int DEALER_HIT_ROLE = 16;

    private final Player player;

    public Dealer(CardGroup cardGroup, CardGenerator cardGenerator) {
        player = new Player(DEALER_NAME, cardGroup, cardGenerator);
    }

    public int calculateScore() {
        return player.calculateScore();
    }

    public void receiveCard(int count) {
        player.receiveCard(count);
    }

    public void hitCardUntilStand() {
        while (isLessThen(DEALER_HIT_ROLE)) {
            player.receiveCard();
        }
    }

    private boolean isLessThen(int score) {
        return player.calculateScore() <= score;
    }

    public boolean isBust() {
        return player.isBust();
    }

    public List<Card> getCards() {
        return player.getCards();
    }

    public int getReceivedCardCount(){
        return player.getCards().size() - GameManager.START_RECEIVE_CARD;
    }
}
