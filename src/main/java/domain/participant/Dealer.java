package domain.participant;

public class Dealer extends Participant {
    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }
}
