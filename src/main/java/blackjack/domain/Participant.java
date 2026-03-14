package blackjack.domain;

import java.util.HashMap;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_SIZE = 2;

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
        return drawnCards.sumScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return drawnCards.sumScore() == BLACKJACK_SCORE && drawnCards.getSize() == BLACKJACK_SIZE;
    }

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public int calculateFinalProfit(Participant dealer) {
        return 0;
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
