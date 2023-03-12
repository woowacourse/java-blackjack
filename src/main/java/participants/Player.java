package participants;

import blackjackgame.Result;

public class Player extends Participant {
    private Result result;

    public Player(Name name) {
        super(name, new Hand());
    }

    @Override
    public void win() {
        result = Result.WIN;
    }

    @Override
    public void lose() {
        result = Result.LOSE;
    }

    @Override
    public void tie() {
        result = Result.TIE;
    }

    @Override
    public void winBlackjack() {
        result = Result.BLACKJACK;
    }

    public Result getResult() {
        return result;
    }
}
