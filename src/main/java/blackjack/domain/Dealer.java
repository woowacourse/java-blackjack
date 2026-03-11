package blackjack.domain;


public class Dealer extends Participant{
    public Dealer() {
        super("딜러");
    }

    public int getAdditionalDrawnCardCount() {
        return drawnCards.getCardNames().size() - 2;
    }

    public boolean isDealerNotDone() {
        return calculateTotalScore() < 17;
    }
}
