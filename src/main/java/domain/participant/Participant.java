package domain.participant;

import domain.Hand;
import domain.Name;
import domain.card.Card;
import domain.game.BlackJackRule;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand = new Hand();

    protected Participant(Name name) {
        this.name = name;
    }

    protected boolean hasTwoCards() {
        return hand.size() == BlackJackRule.INITIAL_HAND_SIZE;
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackjack();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<String> getHandToString() {
        return hand.toStringList();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name.getValue();
    }

    public abstract boolean canDraw();

    public abstract List<String> getInitialOpenCards();
}