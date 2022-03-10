package blackjack;

public abstract class Player implements PlayerInterface {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    protected final String name;
    protected final Deck deck;

    protected Player(String name, Deck deck) {
        checkNull(name);
        this.name = name.trim();
        this.deck = deck;
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    public static abstract class Builder {
        protected static final String ERROR_DECK_IS_NULL = "[ERROR] deck()을 먼저 호출해서 Deck을 초기화해주세요.";

        protected final String name;
        protected Deck deck;

        public Builder(String name) {
            this.name = name;
        }

        public Player.Builder deck(TrumpCard card1, TrumpCard card2) {
            this.deck = new Deck(card1, card2);
            return this;
        }

        public abstract Player build();
    }
}
