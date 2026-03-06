package domain;

public class Dealer {
    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }

    public Boolean determineDealerDealMore() {
        return hand.determineDealerDealMore();
    }

    public String getDealerFinalDisplay() {
        return hand.getFinalDisplay();
    }
}
