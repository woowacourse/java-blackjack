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
        Hand hand = new Hand();
        hand.add(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.SEVEN, Symbol.HEART),
            new Card(Rank.FOUR, Symbol.CLUB)
        ));
        assertThat(Score.totalScoreOf(hand)).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("성공: K, K, A -> 총점 21")
    void totalScoreOf_KingKingAce() {
        Hand hand = new Hand();
        hand.add(List.of(
            new Card(Rank.KING, Symbol.DIAMOND),
            new Card(Rank.KING, Symbol.HEART),
            new Card(Rank.ACE, Symbol.CLUB)
        ));
        assertThat(Score.totalScoreOf(hand)).isEqualTo(Score.valueOf(21));
    }

    @Test
    @DisplayName("성공: A, A, A, A -> 총점 14")
    void totalScoreOf_AceAceAceAce() {
        Hand hand = new Hand();
        hand.add(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.ACE, Symbol.CLUB),
            new Card(Rank.ACE, Symbol.SPADE)
        ));
        assertThat(Score.totalScoreOf(hand)).isEqualTo(Score.valueOf(14));
    }

    @Test
    @DisplayName("성공: A, A, K, K -> 총점 22")
    void totalScoreOf_AceAceKingKing() {
        Hand hand = new Hand();
        hand.add(List.of(
            new Card(Rank.ACE, Symbol.DIAMOND),
            new Card(Rank.ACE, Symbol.HEART),
            new Card(Rank.KING, Symbol.CLUB),
            new Card(Rank.KING, Symbol.SPADE)
        ));
        assertThat(Score.totalScoreOf(hand)).isEqualTo(Score.valueOf(22));
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
    @DisplayName("성공: 22점은 블랙잭 점수가 아니다.")
    void isBlackjackScore_TwentyTwo() {
        Score blackjackScore = Score.valueOf(22);
        assertThat(blackjackScore.isBlackjackScore()).isFalse();
    }

    @Test
    @DisplayName("성공: 21점은 블랙잭 점수이다.")
    void isBlackjackScore_TwentyOne() {
        Score blackjackScore = Score.valueOf(21);
        assertThat(blackjackScore.isBlackjackScore()).isTrue();
    }

    @Test
    @DisplayName("성공: 점수끼리 대소 비교 가능")
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

    @Test
    @DisplayName("성공: 점수끼리 같다 비교 가능")
    void isSameAs() {
        Score score1 = Score.valueOf(10);
        Score score2 = Score.valueOf(10);

        assertThat(score1.isSameAs(score2)).isTrue();
    }
}
