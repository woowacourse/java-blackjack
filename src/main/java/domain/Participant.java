package domain;

public abstract class Participant {
    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public String getFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public int getScore() {
        return hand.calculateTotalScore();
    }
}
