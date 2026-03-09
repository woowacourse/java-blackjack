package blackjack.domain;

import java.util.List;

public class Dealer {
    private Cards drawnCards;

    public Dealer() {
        drawnCards = new Cards();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }

    public String getOneCardName() {
        return drawnCards.getCardNames().getFirst();
    }

    public int getAdditionalDrawnCardCount() {
        return drawnCards.getCardNames().size() - 2;
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
