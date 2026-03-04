import java.util.List;

public class ScoreCalculator {
    public int sumScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getRank();
        }
        return score;
    }
}
