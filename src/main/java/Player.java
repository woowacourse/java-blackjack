import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int BLACK_JACK_COUNT = 21;
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScore();
        }
        return totalScore;
    }

    public boolean canHit() {
        return calculateScore() <= BLACK_JACK_COUNT;
    }
}
