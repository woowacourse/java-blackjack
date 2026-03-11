package blackjack.domain;


public class Dealer extends Participant {

    public Dealer() {
    }

    public String getOneCardName() {
        return getCardNames().getFirst();
    }

    public int getAdditionalDrawnCardCount() {
        return getCardNames().size() - 2;
    }

    public boolean isDealerDone() {
        return calculateTotalScore() >= 17;
    }
}
