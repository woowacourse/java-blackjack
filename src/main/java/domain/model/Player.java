package domain.model;

public class Player implements Person {

    private final String name;
    private Deck deck;

    private Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public static Player of(String name) {
        return new Player(name, null);
    }

    public void calculateFinalSum() {
        deck.calculateFinalSum();
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    // 플레이어에게 초기 생성된 카드 2개가 있는 덱을 부여
    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    // 추가 카드 부여
    public void appendCard(Card card) {
        deck.append(card);
    }

    @Override
    public int getDeckSum() {
        return deck.getSum();
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }


}
