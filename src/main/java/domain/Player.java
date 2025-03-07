package domain;

public class Player {
    public static final int LOSS = -1;

    private final String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public boolean isHandBust() {
        return hand.isBust();
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

    public int getWinLoss(int dealerTotal) {
        if (isHandBust()) {
            return LOSS;
        }
        return Integer.compare(getHandTotal(), dealerTotal);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

}
