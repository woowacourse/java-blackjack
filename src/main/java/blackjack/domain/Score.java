package blackjack.domain;

import java.util.List;

public class Score {

    private int score;

    public void calculateScore(List<TrumpNumber> trumpNumbers){
        score = trumpNumbers.stream().mapToInt(TrumpNumber::getScore).sum();
    }

    public int getScore() {
        return score;
    }
}
