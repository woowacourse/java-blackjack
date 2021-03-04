package blackjack.domain.player;

import blackjack.domain.Result;

public class Player extends Participant {

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

    public boolean canDrawOneMore(int score){
        return score <= 21;
    }
}
