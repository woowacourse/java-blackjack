package domain;

public abstract class Gamer {

    private final String name;
    private final Hand hand = new Hand();

    protected Gamer(String name) {
        this.name = name;
    }

    public void prepareGame(CardDeck deck) {
        addFrom(deck);
        addFrom(deck);
    }

    public boolean isBurst() {
        return hand.isBurst();
    }

    public int getScore() {
        return hand.calculateTotalPoint();
    }

    public void addFrom(CardDeck deck) {
        hand.add(deck.extractCard());
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return this.name;
    }

    public void hit(CardDeck deck) {
        validateHitState();
        addFrom(deck);
    }

    public void validateHitState() {
        if (!canHit()) {
            throw new IllegalStateException("[ERROR] 카드를 더 뽑을 수 없는 상태입니다.");
        }
    }

    public abstract boolean canHit();
}

