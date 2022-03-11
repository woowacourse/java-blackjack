package blackjack.domain;

public abstract class Participant {
    private String name;
    private HoldingCard holdingCard;

    public Participant(String name, HoldingCard holdingCard) {
        this.name = name;
        this.holdingCard = holdingCard;
    }

    void receiveCard(Card card){
        holdingCard.add(card);
    };

    public String getName() {
        return name;
    }

    public HoldingCard getHoldingCard() {
        return holdingCard;
    }

    public boolean isBust() {
        return holdingCard.isBust();
    }

    public abstract boolean isFinished();

    public int calculateScore() {
        int score = holdingCard.calculateTotal();
        if (score > 21) {
            return 0;
        }
        return score;

    }
}
