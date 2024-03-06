import java.util.ArrayList;

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

    public PlayerCards getCards() {
        return cards;
    }
}
