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
}
