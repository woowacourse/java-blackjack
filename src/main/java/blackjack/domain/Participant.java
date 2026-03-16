package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Name name;
    protected final Cards drawnCards;

    protected Participant(String name) {
        this.name = new Name(name);
        this.drawnCards = new Cards();
    }

    public static Participant createPlayer(String name, Money money) {
        return new Player(name, money);
    }

    public static Participant createDealer() {
        return new Dealer();
    }

    public void receiveOneCard(Card card) {
        drawnCards.addCard(card);
    }

    public boolean isDealerNotDone() {
        return false;
    }

    public boolean isBust() {
        return drawnCards.isBust();
    }

    public boolean isBlackjack() {
        return drawnCards.isBlackjack();
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }
}
