import java.util.List;

public class GameService {


    public int calculateScore(List<String> cards) {
        int score = 0;
        for (String card: cards) {
            if (card.equals("J") || card.equals("Q") || card.equals("K")) {
                score += 10;
            }
            else if (card.equals("A")) {

            }
            else {
                score += Integer.parseInt(card);
            }
        }
        return score;
    }
    

    public boolean isBlackjack(int score) {
        if(score == 21) {
            return true;
        }
        return false;
    }

    public boolean isBurst(int score) {
        if(score > 21) {
            return true;
        }
        return false;
    }

    public boolean isHit(int score) {
        if (score <= 16) {
            return true;
        }
        return false;
    }
}
