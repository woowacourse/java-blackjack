public class Dealer extends Player {
    private static final int DRAW_THRESHOLD = 16;

    public Dealer() {
        super("dealer");
    }

    @Override
    public void draw(Cards totalCards) {
        if (canDraw()) {
            super.draw(totalCards);
        }
    }

    private boolean canDraw() {
        return getCards().calculateTotalPoint() <= DRAW_THRESHOLD;
    }
}
