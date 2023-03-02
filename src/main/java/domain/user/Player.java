package domain.user;

import domain.card.Card;
import java.util.List;

public class Player extends User{
    private PlayerStatus status = PlayerStatus.NORMAL;

    public Player(List<Card> firstTurnCards) {
        super(firstTurnCards);
    }

    @Override
    protected void checkBustByScore(int score) {
        if(score > BLACKJACK) {
            status = PlayerStatus.BUST;
        }
    }

    @Override
    protected boolean isBust() {
        return status.equals(PlayerStatus.BUST);
    }
}
