package domain.gamer;

import domain.card.CardDeck;
import domain.card.Hand;

public abstract class Gamer {

    private final Nickname name;
    private final Hand hand = new Hand();

    protected Gamer(Nickname name) {
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

    private void addFrom(CardDeck deck) {
        hand.add(deck.drawCard());
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return this.name.getName();
    }

    public void hit(CardDeck deck) {
        validateHitState();
        addFrom(deck);
    }

    private void validateHitState() {
        if (!canHit()) {
            throw new IllegalStateException("[ERROR] 카드를 더 뽑을 수 없는 상태입니다.");
        }
    }

    public abstract boolean canHit();
}

