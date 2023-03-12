package domain.user;

import domain.card.Card;
import domain.state.PlayerStatus;
import domain.state.State;

import java.util.List;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;

    public Player(Name name, List<Card> firstTurnCards) {
        super(firstTurnCards);
        this.name = name;
    }

    @Override
    protected void checkBustByScore() {
        if (hand.isBust()) {
            status = PlayerStatus.BUST;
        }
    }

    @Override
    public boolean isUserStatus(State status) {
        return this.status.equals(status);
    }

    @Override
    public String getName() {
        return name.getName();
    }
}
