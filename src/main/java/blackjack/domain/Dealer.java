package blackjack.domain;

class Dealer implements Player {
    private final Name name;

    private Dealer(final Name name) {
        this.name = name;
    }

    public static Dealer create() {
        return new Dealer(Name.createDealerName());
    }

    public String getName() {
        return name.getValue();
    }
}
