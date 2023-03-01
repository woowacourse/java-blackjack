package domain;

public class Dealer extends Player {
    public Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public boolean isSumUnderStandard() {
        return sumOfPlayerCards() < 17;
    }

}
