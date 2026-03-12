package blackjack.domain;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return calculateCardsValue() < 17;
    }

}
