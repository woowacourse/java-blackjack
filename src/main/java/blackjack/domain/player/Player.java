package blackjack.domain.player;

import blackjack.domain.Result;

public class Player extends Participant {

    private static final int LIMIT_SCORE = 21;

    private Result result;

    public Player() {
        this("null");
    }

    public Player(String name) {
        super(name);
    }

    public void compareWithDealer(Dealer dealer) {
        result = dealer.compare(this);
    }

    public Result getResult() {
        return result;
    }

    public boolean canDrawOneMore(){
        return getScore() <= LIMIT_SCORE;
    }
}
