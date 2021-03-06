package blackjack.domain.player;

public class Player extends Participant {

    private static final int LIMIT_SCORE = 21;

    public Player() {
        this("null");
    }

    public Player(String name) {
        super(name);
    }

    public void compareWithDealer(Dealer dealer) {
        dealer.compare(this);
    }

    public boolean canDrawOneMore(){
        return getScore() <= LIMIT_SCORE;
    }
}
