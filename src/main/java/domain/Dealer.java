package domain;

public class Dealer extends GameParticipant {

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer init() {
        return new Dealer(Cards.empty());
    }

    public static Dealer of(Cards cards) {
        return new Dealer(cards);
    }
}
