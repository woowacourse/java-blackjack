package domain.player;


public class Dealer extends Player {

    private static final int DEALER_STOP_SCORE = 17;

    public Dealer() {
        super();
    }

    public boolean canStand() {
        return score() >= DEALER_STOP_SCORE;
    }

    public String firstCard(){
        return handCard.firstCard();
    }

}
