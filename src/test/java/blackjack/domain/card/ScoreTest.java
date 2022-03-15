package blackjack.domain.card;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("정수값으로 스코어를 생성할 수 있다.")
    public void testScoreCreation() {
        // given
        int value = 10;
        // when
        Score score = new Score(value);
        // then
        Assertions.assertThat(score).isNotNull();
    }

    @Test
    @DisplayName("음수로는 스코어를 생성할 수 없다.")
    public void throwsExceptionWithNegativeInteger() {
        // when
        int value = -1;
        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Score(value));
    }

    @DisplayName("에이스가 없는 경우의 점수를 계산한다.")
    @Test
    public void testCalculateDefaultCondition() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.KING)
        );

        //when
        Score score = Score.addUpPointToScore(cards);

        //then
        Assertions.assertThat(score).isEqualTo(new Score(15));

    }

    @DisplayName("에이스가 포함된 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithAce() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.CLOVER, Denomination.FIVE),
            new Card(Suit.DIAMOND, Denomination.ACE)
        );

        //when
        Score score = Score.addUpPointToScore(cards);

        //then
        Assertions.assertThat(score).isEqualTo(new Score(16));
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.HEART, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.ACE),
            new Card(Suit.SPADE, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.KING)
        );

        //when
        Score score = Score.addUpPointToScore(cards);

        //then
        Assertions.assertThat(score).isEqualTo(new Score(14));
    }

    @DisplayName("에이스가 여러개 있는 경우 유리하게 점수를 계산한다.")
    @Test
    public void testSumPointWithMultipleAce2() {
        //given
        List<Card> cards = List.of(
            new Card(Suit.SPADE, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.CLOVER, Denomination.NINE)
        );

        //when
        Score score = Score.addUpPointToScore(cards);

        //then
        Assertions.assertThat(score).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("다른 스코어와 대소를 비교할 수 있다.")
    public void compareGreaterThanOther() {
        // given
        Score aScore = new Score(10);
        Score bScore = new Score(15);
        // when
        boolean isGreaterThan = bScore.isGreaterThan(aScore);
        // then
        Assertions.assertThat(isGreaterThan).isTrue();
    }

    @Test
    @DisplayName("스코어가 버스트인지 확인할 수 있다.")
    public void checkIfScoreIsBust() {
        // given
        Score score = new Score(22);
        // when
        boolean isBust = score.isBust();
        // then
        Assertions.assertThat(isBust).isTrue();
    }
}