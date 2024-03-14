package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("성공: 인스턴스 캐싱")
    void cache() {
        Score cachedScore = Score.valueOf(31);
        Score notCachedScore = Score.valueOf(32);

        Assertions.assertAll(
            () -> assertThat(cachedScore).isSameAs(Score.valueOf(31)),
            () -> assertThat(notCachedScore).isNotSameAs(Score.valueOf(32))
        );
    }

    @Test
    @DisplayName("성공: K, 7, 4 -> 총점 21")
    void totalScoreOf_KingSevenFour() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.SEVEN, Symbol.HEART),
            new Card(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(Score.totalScoreOf(participantCards)).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("성공: K, K, A -> 총점 21")
    void totalScoreOf_KingKingAce() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.KING, Symbol.HEART),
            new Card(Rank.ACE, Symbol.CLUB)
        ));
        assertThat(Score.totalScoreOf(participantCards)).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("성공: A, A, A, A -> 총점 14")
    void totalScoreOf_AceAceAceAce() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.ACE, Symbol.CLUB),
            new Card(Rank.ACE, Symbol.SPADE)
        ));
        assertThat(Score.totalScoreOf(participantCards)).isEqualTo(Score.valueOf(14));
    }

    @Test
    @DisplayName("성공: A, A, K, K -> 총점 22")
    void totalScoreOf_AceAceKingKing() {
        ParticipantCards participantCards = new ParticipantCards();
        participantCards.receive(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.KING, Symbol.SPADE)
        ));
        assertThat(Score.totalScoreOf(participantCards)).isEqualTo(Score.valueOf(22));
    }

    @Test
    @DisplayName("성공: 점수를 더할 수 있다.")
    void add() {
        Score score = Score.valueOf(10);
        Score otherScore = Score.valueOf(5);

        assertThat(score.add(otherScore)).isEqualTo(Score.valueOf(15));
    }

    @Test
    @DisplayName("성공: 22점은 버스트이다.")
    void isBust_True_isNotBust_False() {
        Score bustScore = Score.valueOf(22);
        Assertions.assertAll(
            () -> assertThat(bustScore.isBust()).isTrue(),
            () -> assertThat(bustScore.isNotBust()).isFalse()
        );
    }

    @Test
    @DisplayName("성공: 21점은 버스트가 아니다.")
    void isBust_False_isNotBust_True() {
        Score bustScore = Score.valueOf(21);
        Assertions.assertAll(
            () -> assertThat(bustScore.isBust()).isFalse(),
            () -> assertThat(bustScore.isNotBust()).isTrue()
        );
    }

    @Test
    @DisplayName("성공: 점수끼리 비교가 가능하다.")
    void isGreaterThan_isLessThan() {
        Score greater = Score.valueOf(10);
        Score less = Score.valueOf(9);

        Assertions.assertAll(
            () -> assertThat(greater.isGreaterThan(less)).isTrue(),
            () -> assertThat(less.isGreaterThan(greater)).isFalse(),
            () -> assertThat(less.isLessThan(greater)).isTrue(),
            () -> assertThat(greater.isLessThan(less)).isFalse()
        );
    }
}
