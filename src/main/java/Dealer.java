public class Dealer extends Player {

    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super("dealer");
    }

    @Override
    public void hit(Cards totalCards) {
        if (canHit()) {
            super.hit(totalCards);
        }
    }

    private boolean canHit() {
        return getCards().calculateTotalPoint() <= HIT_THRESHOLD;
    }


}
