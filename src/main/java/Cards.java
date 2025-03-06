import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BURST_SCORE = 22;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateTotalPoint() {
        int totalPoint = sumPointWithoutAce();
        totalPoint = sumAcePoint(totalPoint);

        return totalPoint;
    }

    private int sumPointWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.getNumber().getPoint())
                .sum();
    }

    private int sumAcePoint(int totalPoint) {
        List<Number> aces = extractAces();

        for (Number ace : aces) {
            int acePoint = calculateAcePoint(totalPoint, ace);
            totalPoint += acePoint;
        }

        return totalPoint;
    }

    private List<Number> extractAces() {
        return cards.stream()
                .filter(Card::isAce)
                .map(Card::getNumber)
                .toList();
    }

    private int calculateAcePoint(int totalPoint, Number ace) {
        if (totalPoint + Number.SOFT_ACE.getPoint() >= BURST_SCORE) {
            return ace.getPoint();
        }
        return Number.SOFT_ACE.getPoint();
    }

    public boolean isBurst() {
        return calculateTotalPoint() >= BURST_SCORE;
    }

    public Card extractCard() {
        return cards.removeLast();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
