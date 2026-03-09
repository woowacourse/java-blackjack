package domain.model;

public class Player implements Person {

    private final String name;
    private Deck deck;
    private PlayerStatus playerStatus = PlayerStatus.NONE;
    private int finalSum = 0;

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

    public Deck getDeck() {
        return deck;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public int getFinalSum() {
        return finalSum;
    }

    public void calculateFinalSum() {
        finalSum = deck.calculateFinalSum();
    }

    // 플레이어에게 초기 생성된 카드 2개가 있는 덱을 부여
    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    // 추가 카드 부여
    public void appendCard(Card card) {
        deck.append(card);
    }

    public boolean isBurst() {
        return deck.getDeckStatus() == DeckStatus.BURST;
    }

    public boolean isAlive() {
        return deck.getDeckStatus() == DeckStatus.ALIVE;
    }

    public void changeStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
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
