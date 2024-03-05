package player;

public class Dealer extends Player {

    public boolean determineResult(int score) {
        // TODO: 무승부 처리
        return hand.calculateScore() > score;
    }
}
