package model;

public class Dealer extends User {

    private static final Dealer DEALER = new Dealer("딜러");

    private Dealer(final String name) {
        super(name);
    }

    public static Dealer getInstance() {
        return Dealer.DEALER;
    }

    public String getName() {
        return super.getName();
    }
}
