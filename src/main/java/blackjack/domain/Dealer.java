package blackjack.domain;


public class Dealer extends Participant{
    public Dealer() {
        super();
    }

    public int getAdditionalDrawnCardCount() {
        return drawnCards.getCardNames().size() - 2;
    }

    public boolean isDealerDone() {
        return calculateTotalScore() >= 17;
    }
}
