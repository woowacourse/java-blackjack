package blackjack.domain.player;

import java.util.List;

import blackjack.domain.card.Drawable;
import blackjack.domain.card.HoldCards;
import blackjack.domain.Score;
import blackjack.domain.card.Card;

public class Player {

    public static final int BLACKJACK_NUMBER = 21;

    private final HoldCards holdCards;
    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(Name name, HoldCards holdCards, BettingAmount bettingAmount) {
        this.name = name;
        this.holdCards = holdCards;
        this.bettingAmount = bettingAmount;
    }

    public Player(Name name, HoldCards holdCards) {
        this(name, holdCards, null);
    }

    public void drawCard(Drawable drawable) {
        holdCards.add(drawable.draw());
    }

    public Score compete(Player otherPlayer) {
        if (!this.isBust() && !otherPlayer.isBust()) {
            return Score.compete(this.getTotalNumber(), otherPlayer.getTotalNumber());
        }
        if (this.isBust() && otherPlayer.isBust()) {
            return Score.DRAW;
        }
        if (otherPlayer.isBust()) {
            return Score.WIN;
        }
        return Score.LOSE;
    }

    public boolean isBust() {
        return getTotalNumber() > BLACKJACK_NUMBER;
    }

    public int getTotalNumber() {
        return holdCards.getTotalNumber();
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHoldCards() {
        return holdCards.getCards();
    }

    public Player copy() {
        return new Player(name, holdCards.copy());
    }
}
