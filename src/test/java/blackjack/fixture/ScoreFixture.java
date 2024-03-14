package blackjack.fixture;

import blackjack.domain.Score;

public class ScoreFixture {

    public static Score 점수(int score) {
        return Score.valueOf(score);
    }
}
