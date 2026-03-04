import java.util.List;

public class ScoreCalculator {
    private int sumScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getRank();
        }
        return score;
    }

    public int calculateScore(List<Card> hand) {
        int currentScore = sumScore(hand);

        Card findCard
                = hand.stream().filter(c -> c.getRank() == 1).findAny().orElse(null);

        if (findCard == null) {
            return currentScore;
        }

        if (currentScore + 10 > 21) {
            return currentScore;
        }

        return currentScore + 10;
    }
}
