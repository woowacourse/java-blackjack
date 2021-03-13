package blackjack.domain.participants;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    public Player(final Name name, final double money) {
        super(name, money);
    }

    public Player(final String name, final double money) {
        super(new Name(name), money);
    }

    public Player(final String name) {
        super(name);
    }

    @Override
    public Result decideWinner(final Participant participant) {
        if (this.isBlackjack() && !participant.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (this.isBust()) {
            return Result.LOSE;
        }
        if (participant.isBust()) {
            return Result.WIN;
        }
        return decideWinnerWithScores(participant);
    }

    @Override
    public List<Card> initialCards() {
        return new ArrayList<>(getPlayerCards());
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }

}
