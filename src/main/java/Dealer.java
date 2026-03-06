import java.util.ArrayList;

public class Dealer {
    private Cards drawnCards;

    public Dealer() {
        drawnCards = new Cards();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public Cards getDrawnCards() {
        return drawnCards;
    }

    public boolean isBust() {
        return drawnCards.sumScore() > 21;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public boolean isDealerDone() {
        return calculateTotalScore() >= 17;
    }
}
