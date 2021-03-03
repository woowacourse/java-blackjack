package blackjack.domain;

public class Dealer extends Participant {
    private static final int STANDARD = 16;

    public Dealer() {
    }

    public boolean isUnderStandard() {
        return calculateTotalValue() <= STANDARD;
    }

    @Override
    public void draw(){

    }
}
