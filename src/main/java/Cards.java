import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
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