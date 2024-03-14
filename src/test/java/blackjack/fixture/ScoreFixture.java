package blackjack.fixture;

import blackjack.domain.card.Score;

public class ScoreFixture {

    public static Score 점수(int score) {
        return Score.valueOf(score);
    }
}
