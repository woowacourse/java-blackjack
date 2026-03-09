package blackjack.domain;

import java.util.List;

public class Player {
    private final String name;

    private Cards drawnCards;

    public Player(String name) {
        this.name = name;
        this.drawnCards = new Cards();
    }

    public String getName() {
        return name;
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }

    public boolean isBust() {
        return drawnCards.sumScore() > 21;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }
}
