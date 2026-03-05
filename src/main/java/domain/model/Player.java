package domain.model;

public class Player implements Person {

    private final String name;
    // TODO: 덱(카드 여러개) 필드 추가
    private Deck deck;

    private Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public static Player of(String name) {
        return new Player(name, null);
    }

    public String getName() {
        return name;
    }

    // 플레이어에게 초기 생성된 카드 2개가 있는 덱을 부여
    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    @Override
    public int getDeckSum() {
        return 0;
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }
}
