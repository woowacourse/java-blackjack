package domain;

public class Dealer extends Gamer {

    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean hit(Cards totalCards) {
        if (canHit()) {
            add(totalCards);
            return true;
        }
        return false;
    }

    private boolean canHit() {
        return getCards().calculateTotalPoint() <= HIT_THRESHOLD;
    }

}
