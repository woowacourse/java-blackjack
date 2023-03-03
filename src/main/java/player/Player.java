package player;

import java.util.List;

import blackjackGame.Result;
import card.Card;

public class Player {
    private final Name name;
    private final Hand hand = new Hand();
    private Result result;

    public Player(Name name) {
        this.name = name;
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

    public boolean isBust() {
        return hand.calculateScore() >= 22;
    }

    public Name getName() {
        return name;
    }

    public void win() {
        result = Result.WIN;
    }

    public void lose() {
        result = Result.LOSE;
    }

    public void tie() {
        result = Result.TIE;
    }

    public Result getResult() {
        return result;
    }
}
