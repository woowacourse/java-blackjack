package blackjack.domain;

import java.util.HashMap;
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

    public Money calculateFinalProfit(Participant dealer) {
        return new Money(0);
    }

    public GameResult judgeResult(List<Participant> players, Participant dealer) {
        return new GameResult(new HashMap<>(), new HashMap<>());
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }
}
