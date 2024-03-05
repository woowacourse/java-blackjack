import java.util.Collections;
import java.util.List;

public class Player {

    private final PlayerName playerName;
    private final List<Card> cards;
    private final Score score;

    public Player(String name, List<Card> cards) {
        this.playerName = new PlayerName(name);
        this.cards = cards;
        this.score = new Score(0);
        cards.forEach(score::addScore);
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
