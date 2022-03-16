package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public abstract class State {
    protected static final String HIT = "Hit";
    protected static final String BLACKJACK = "Blackjack";
    protected static final String BUST = "Bust";
    public static final String FINISH = "Finish";

    protected final Cards cards;
    protected String sign;

    public State() {
        this.cards = new Cards();
        this.sign = HIT;
    }

    protected State(Cards cards, String state) {
        this.cards = cards;
        this.sign = state;
    }

    public boolean isWin(State otherState) {
        if (sign.equals(otherState.sign) && isNotFinish(otherState)) {
            return false;
        }
        return cards.sumScore() > otherState.cards.sumScore();
    }

    public boolean isLose(State otherState) {
        if (sign.equals(otherState.sign) && isNotFinish(otherState)) {
            return false;
        }
        return cards.sumScore() < otherState.cards.sumScore();
    }

    private boolean isNotFinish(State otherState) {
        return !this.sign.equals(FINISH) || !otherState.sign.equals(FINISH);
    }

    public boolean isDraw(State otherState) {
        if (sign.equals(otherState.sign)) {
            return true;
        }
        return cards.sumScore() == otherState.cards.sumScore();
    }

    public int sumScore() {
        return cards.sumScore();
    }

    public List<String> getCards() {
        return cards.getValues();
    }

    public abstract State getCopyInstance();

    public abstract boolean canHit();

    public abstract State addCard(Card card);
}
