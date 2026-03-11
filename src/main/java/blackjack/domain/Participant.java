package blackjack.domain;

import java.util.HashMap;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Cards drawnCards;

    protected Participant(String name) {
        this.name = name;
        this.drawnCards = new Cards();
    }

    public String getName() {
        return name;
    }

    public boolean isDealerNotDone() {
        return false;
    }

    public static Participant createPlayer(String name) {
        return new Player(name);
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

    public int calculateTotalScore() {
        return drawnCards.sumScore();
    }

    public GameResult judgeResult(List<Participant> players, Participant dealer) {
        return new GameResult(new HashMap<>(), new HashMap<>());
    }
}
