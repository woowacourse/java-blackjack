import java.util.ArrayList;
import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int sumScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.translateToScore();
        }
        int aceCount = countAces();
        while (totalScore > 21 && aceCount > 0) {
            totalScore -= 10;
            aceCount--;
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    private int countAces() {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank().equals("A")) {
                count++;
            }
        }
        return count;
    }
}