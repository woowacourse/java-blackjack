package domain.user;

import domain.card.Card;
import java.util.List;

public class Player extends User{
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;

    public Player(Name name, List<Card> firstTurnCards) {
        super(firstTurnCards);
        this.name = name;
    }

    @Override
    protected void checkBustByScore() {
        if(score.getScore() > BLACKJACK) {
            status = PlayerStatus.BUST;
        }
    }

    @Override
    protected UserStatus getStatus() {
        return status;
    }

    public String getName() {
        return name.getName();
    }
}
