package model;

public class Player {

    private final String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean resolveBust() {
        if (isHandBust() && containsOriginalAce()) {
            setOriginalAceValueToOne();
            resolveBust();
        }
        return !isHandBust();
    }

    public boolean isNotDealer() {
        return true;
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public boolean isHandBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public boolean containsOriginalAce() {
        return hand.containsOriginalAce();
    }

    public void setOriginalAceValueToOne() {
        hand.setOriginalAceValueToOne();
    }

    public void setHandTotalToZero() {
        hand.setAllCardValueToZero();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
