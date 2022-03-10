package blackjack;

public class Dealer extends Player {

    protected Dealer(String name, Deck deck) {
        super(name, deck);
    }

    public static class Builder extends Player.Builder {
        private static final String NAME = "딜러";

        public Builder() {
            super(NAME);
        }

        @Override
        public Dealer build()  {
            if (deck == null) {
                throw new RuntimeException(ERROR_DECK_IS_NULL);
            }
            return new Dealer(this.name, this.deck);
        }
    }
}
