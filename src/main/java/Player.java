import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final PlayerName name;
    private final PlayerCards cards;

    public Player(String name) {
        this.name = new PlayerName(name);
        this.cards = new PlayerCards(new ArrayList<>());
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public boolean isNotBust() {
        return cards.hasScoreUnderBustThreshold();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
