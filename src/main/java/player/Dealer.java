package player;

import java.util.List;
import java.util.Map;

import blackjackGame.Result;
import card.Card;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    private final Name name;
    private final Hand hand = new Hand();
    private final DealerResult dealerResult = new DealerResult();


    public Dealer() {
        this.name = new Name(DEALER_NAME);
    }

    public void hit(Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> showCards() {
        return hand.getCards();
    }

    public Card showOneCard() {
        return hand.pickFirstCard();
    }

    public boolean isUnderScore() {
        return hand.calculateScore() <= 16;
    }

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }

    public Name getName() {
        return name;
    }

    public void lose() {
        dealerResult.addLose();
    }

    public void win() {
        dealerResult.addWin();
    }

    public void tie() {
        dealerResult.addTie();
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult.getDealerResult();
    }
}
