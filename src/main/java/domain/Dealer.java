package domain;

public final class Dealer extends Player {

    private Dealer(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Dealer create() {
        return new Dealer(Name.from("딜러"), new Cards());
    }
}
