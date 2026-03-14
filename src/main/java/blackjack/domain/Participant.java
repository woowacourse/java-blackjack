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

    public String getName() {
        return name.getName();
    }

    public boolean isDealerNotDone() {
        return false;
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

    public List<String> getCardNames() {
        return drawnCards.getCardNames();
    }

    public boolean isBust() {
        return drawnCards.sumScore() > 21;
    }

    public boolean isBlackjack() {
        return drawnCards.sumScore() == 21 && drawnCards.getSize() == 2;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public GameResult judgeResult(List<Participant> players, Participant dealer) {
        return new GameResult(new HashMap<>(), new HashMap<>());
    }

    public int calculateFinalProfit(Participant dealer) {
        return 0;
    }
}
