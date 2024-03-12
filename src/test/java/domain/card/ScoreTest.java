package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("성공: 카드들의 총점 계산 가능")
    void totalScoreOf() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.SEVEN, Symbol.HEART),
            new Card(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(Score.totalScoreOf(participantCards)).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("성공: 점수를 더할 수 있다.")
    void add() {
        Score score = new Score(10);
        Score otherScore = new Score(5);

        assertThat(score.add(otherScore)).isEqualTo(new Score(15));
    }

    @Test
    @DisplayName("성공: 22점은 버스트이다.")
    void isBust_True_isNotBust_False() {
        Score bustScore = new Score(22);
        Assertions.assertAll(
            () -> assertThat(bustScore.isBust()).isTrue(),
            () -> assertThat(bustScore.isNotBust()).isFalse()
        );
    }

    @Test
    @DisplayName("성공: 21점은 버스트가 아니다.")
    void isBust_False_isNotBust_True() {
        Score bustScore = new Score(21);
        Assertions.assertAll(
            () -> assertThat(bustScore.isBust()).isFalse(),
            () -> assertThat(bustScore.isNotBust()).isTrue()
        );
    }

    @Test
    @DisplayName("성공: 점수끼리 비교가 가능하다.")
    void isGreaterThan_isLessThan() {
        Score greater = new Score(10);
        Score less = new Score(9);

        Assertions.assertAll(
            () -> assertThat(greater.isGreaterThan(less)).isTrue(),
            () -> assertThat(less.isGreaterThan(greater)).isFalse(),
            () -> assertThat(less.isLessThan(greater)).isTrue(),
            () -> assertThat(greater.isLessThan(less)).isFalse()
        );
    }
}
