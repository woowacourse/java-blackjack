import java.util.Collections;
import java.util.List;

public class Gamer {

    private final GamerName name;
    protected final GamerCards cards;

    public Gamer(String name, GamerCards cards) {
        this.name = new GamerName(name);
        this.cards = cards;
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
