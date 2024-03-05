import java.util.ArrayList;

public class Player {

    private final PlayerName playerName;
    private final Cards cards;
    private final Score score;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.cards = new Cards(new ArrayList<>());
        this.score = new Score(0);
    }

    public void hit(Card card) {
        cards.addCard(card);
        score.addScore(card);
    }

    public boolean hasScoreUnderThreshold() {
        return score.isLowerThanBustThreshold();
    }

    public String getPlayerName() {
        return playerName.getValue();
    }

    public Cards getCards() {
        return cards;
    }
}
