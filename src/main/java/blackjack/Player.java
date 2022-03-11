package blackjack;

public abstract class Player implements PlayerInterface {
    private static final String ERROR_NULL = "[ERROR] 입력된 이름이 없습니다.";

    protected final String name;
    protected final Deck deck;

    public Player(String name) {
        checkNull(name);
        this.name = name.trim();
        this.deck = new Deck();
    }

    private void checkNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    @Override
    public void addCard(TrumpCard card) {
        this.deck.add(card);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
