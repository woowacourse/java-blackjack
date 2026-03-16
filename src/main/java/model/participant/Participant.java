package model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;
import model.game.GameStatus;

public abstract class Participant {
    private final String name;
    protected final Cards hand;
    private GameStatus status;

    protected Participant(String name) {
        this.name = name;
        this.hand = Cards.from(new ArrayList<>());
        this.status = GameStatus.NEED_DEALOUT;
    }

    public List<Card> receive(Card card) {
        hand.add(card);

        status = status.getNextStatus(hand);

        return hand.asList();
    }

    public boolean canReceive() {
        return status.canReceive();
    }

    public boolean isBust() {
        return status == GameStatus.BUST;
    }

    public boolean isBlackjack() {
        return status == GameStatus.BLACKJACK;
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Participant that)) {
            return false;
        }
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public abstract List<Card> open();
}
