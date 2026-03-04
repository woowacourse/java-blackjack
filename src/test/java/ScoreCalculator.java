import java.util.List;

public class ScoreCalculator {
    public int sumScore(List<Card> hand) {
        int score = 0;
        for (Card card : hand) {
            score += card.getRank();
        }
        return score;
    }

    public int checkAce(int currentScore, List<Card> hand) {
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
