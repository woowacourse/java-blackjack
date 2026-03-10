package domain;

public class Dealer extends Player {
    private final int DEALER_DRAW_CONDITION = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canDraw(){
        return this.getScore() <= DEALER_DRAW_CONDITION;
    }

    public String getOpenCard() {
        return getHandToString().getFirst();
    }
}
