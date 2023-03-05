package blackjack.domain.participant.player;

import blackjack.domain.game.Result;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Participant;

public class Player extends Participant {
    private Result result;

    public Player(Name name) {
        super(name, new Hand());
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
