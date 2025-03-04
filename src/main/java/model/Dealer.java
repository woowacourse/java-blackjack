package model;

public class Dealer {
    private final PlayerHand playerHand;

    public Dealer() {
        this.playerHand = new PlayerHand();
    }

    public void receiveCard(Card card) {
        playerHand.add(card);
    }

    public boolean checkScoreUnderSixteen() {
        return playerHand.calculateScoreSum() <= 16;
    }
}
