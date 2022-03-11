package blackjack;

public abstract class Player implements PlayerInterface {
    protected static final String ERROR_DECK_IS_NULL = "[ERROR] deck()을 먼저 호출해서 Deck을 초기화해주세요.";
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
}
