package blackjack.domain.participants;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public Result decideWinner(Participant participant) {
        if (this.isBust() || participant.calculate() >= this.calculate()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    @Override
    public List<Card> showCards() {
        return new ArrayList<>(getPlayerCards());
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return !isBust();
    }

}
