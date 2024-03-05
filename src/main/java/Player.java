import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final List<Card> cards;
    private final Score score;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.cards = new ArrayList<>();
        this.score = new Score(0);
    }

    public void hit(Card card) {
        cards.add(card);
        score.addScore(card);
    }

    public boolean hasScoreUnderThreshold() {
        return score.isLowerThanBustThreshold();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
