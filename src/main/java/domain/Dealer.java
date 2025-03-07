package domain;

public class Dealer extends Player {

    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean dealerHit(Cards totalCards) {
        if (canHit()) {
            super.hit(totalCards);
            return true;
        }
        return false;
    }

    private boolean canHit() {
        return getCards().calculateTotalPoint() <= HIT_THRESHOLD;
    }


}
