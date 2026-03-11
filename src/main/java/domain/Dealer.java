package domain;

public class Dealer extends Participant {
    private static final int bustThreshold = 17;

    private Dealer(Name name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(new Name("딜러"));
    }
    public boolean canReceiveCard() {
        return cards.canReceiveCard(bustThreshold);
    }
}
