package domain;

public class Dealer extends Participant {

    public static final int DRAW_BOUNDARY = 16;

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }

    public boolean hasToDraw() {
        return this.getCardScore() <= DRAW_BOUNDARY;
    }
}
