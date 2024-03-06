import java.util.Collections;
import java.util.List;

public class PlayerCards {

    private static final int BUST_THRESHOLD = 21;
    private static final int CONDITION_DECIDING_A_SCORE = 10;
    private static final int A_SCORE_GAP = 10;

    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int totalScore = sumExceptAceCards();
        totalScore += sumAceCards(totalScore);
        return totalScore;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::score)
                .sum();
    }

    private int sumAceCards(int score) {
        return cards.stream()
                .filter(Card::isAce)
                .mapToInt(card -> decideAceScore(score))
                .sum();
    }

    private int decideAceScore(int totalScore) {
        if (totalScore <= CONDITION_DECIDING_A_SCORE) {
            return CardNumber.A.getScore() + A_SCORE_GAP;
        }
        return CardNumber.A.getScore();
    }

    public boolean isNotBust() {
        return calculateScore() <= BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
