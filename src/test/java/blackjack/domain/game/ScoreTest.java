package blackjack.domain.game;

import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER9;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static blackjack.domain.fixture.CardRepository.CLOVER_KING;
import static blackjack.domain.fixture.CardRepository.CLOVER_QUEEN;
import static blackjack.domain.fixture.CardRepository.HEART_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @DisplayName("정적 팩토리 메소드 valueOf 로 새로운 점수 인스턴스를 생성한다.")
    @Test
    void valueOf_createNewScore() {
        Score score = Score.valueOf(1);

        assertThat(score).isNotNull();
    }

    @DisplayName("정적 팩토리 메소드 valueOf 는 캐싱된 점수 인스턴스를 가져온다.")
    @Test
    void valueOf_getCache() {
        Score score = Score.valueOf(10);
        Score sameScore = Score.valueOf(10);

        assertThat(score).isEqualTo(sameScore);
    }

    @DisplayName("인스턴스 메서드 add 는 다른 Score 인스턴스를 받아 더한 값의 Score 인스턴스를 반환한다.")
    @Test
    void add() {
        Score score = Score.valueOf(10);
        Score anotherScore = Score.valueOf(15);

        Score newScore = score.add(anotherScore);

        assertThat(newScore.getValue()).isEqualTo(25);
    }

    @DisplayName("isGreaterThan 에 점수를 전달하면, 현재 점수가 전달된 점수보다 더 큰지에 대한 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"5,true", "10,false", "15,false"})
    void isGreaterThan(int input, boolean expected) {
        // given
        Score score = Score.valueOf(10);

        // when
        boolean actual = score.isGreaterThan(Score.valueOf(input));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("isLessThan 에 점수를 전달하면, 현재 점수가 전달된 점수보다 더 작은지에 대한 여부를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"5,false", "10,false", "15,true"})
    void isLessThan(int input, boolean expected) {
        // given
        Score score = Score.valueOf(10);

        // when
        boolean actual = score.isLessThan(Score.valueOf(input));

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateSumFrom 에 카드패를 전달하면 각 카드가 지닌 값들의 합을 합산하여 반환한다.")
    @Test
    void calculateSumFrom_withHand() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);

        Score actual = Score.calculateSumFrom(hand);
        Score expected = Score.valueOf(9);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateSumFrom 에 카드 리스트를 전달하면 각 카드가 지닌 값들의 합을 합산하여 반환한다.")
    @Test
    void calculateSumFrom_withCardList() {
        List<Card> cards = List.of(CLOVER4, CLOVER5);

        Score actual = Score.calculateSumFrom(cards);
        Score expected = Score.valueOf(9);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("compareTo 는 각 점수 인스턴스의 크기를 비교한다.")
    @Nested
    class CompareToTest {
        @DisplayName("기준 인스턴스의 value 값이 비교 대상보다 더 클 경우 양수를 반환한다.")
        @Test
        void compareTo_returnPositiveIfBiggerThanTarget() {
            Score score = Score.valueOf(15);
            Score smallerScore = Score.valueOf(10);

            int actual = score.compareTo(smallerScore);

            assertThat(actual).isPositive();
        }

        @DisplayName("기준 인스턴스의 value 값이 비교 대상과 동일한 경우 0을 반환한다.")
        @Test
        void compareTo_returnZeroIfSameAsTarget() {
            Score score = Score.valueOf(10);
            Score sameScore = Score.valueOf(10);

            int actual = score.compareTo(sameScore);

            assertThat(actual).isZero();
        }

        @DisplayName("기준 인스턴스의 value 값이 비교 대상보다 더 작을 경우 음수를 반환한다.")
        @Test
        void compareTo_returnNegativeIfSmallerThanTarget() {
            Score score = Score.valueOf(15);
            Score biggerScore = Score.valueOf(20);

            int actual = score.compareTo(biggerScore);

            assertThat(actual).isNegative();
        }
    }

    @DisplayName("calculateSumFrom 은 ACE 가 포함된 패를 전달 받았을 때 최대한 버스트되지 않고 최대가 되도록 ACE 값을 선택하여 점수의 합을 반환한다.")
    @Nested
    class CalculateSumFromTest {

        @DisplayName("패가 ACE, 2 일경우 ACE 는 11로 취급된다.")
        @Test
        void calculateSumFrom_withAceAnd2() {
            // given
            Hand hand = Hand.of(CLOVER_ACE, CLOVER2);

            // when
            Score actual = Score.calculateSumFrom(hand);
            Score expected = Score.valueOf(13);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 ACE, ACE 일경우 첫번째 ACE는 11로, 두번째 ACE는 1로 취급된다.")
        @Test
        void calculateSumFrom_withTwoAces() {
            // given
            Hand hand = Hand.of(CLOVER_ACE, HEART_ACE);

            // when
            Score actual = Score.calculateSumFrom(hand);
            Score expected = Score.valueOf(12);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, QUEEN, ACE 일경우 ACE는 1로 취급된다.")
        @Test
        void calculateSumFrom_withKingQueenAndAce() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER_QUEEN);
            hand.add(CLOVER_ACE);

            // when
            Score actual = Score.calculateSumFrom(hand);
            Score expected = Score.valueOf(21);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, 9, ACE, ACE 일경우 두 ACE는 모두 1로 취급된다.")
        @Test
        void calculateSumFrom_withKingAceAnd9() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER9);
            hand.add(CLOVER_ACE);
            hand.add(HEART_ACE);

            // when
            Score actual = Score.calculateSumFrom(hand);
            Score expected = Score.valueOf(21);

            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패가 KING, QUEEN, ACE, ACE 일경우 두 ACE는 모두 1로 취급된다.")
        @Test
        void calculateSumFrom_withKingQueenAndTwoAces() {
            // given
            Hand hand = Hand.of(CLOVER_KING, CLOVER_QUEEN);
            hand.add(CLOVER_ACE);
            hand.add(HEART_ACE);

            // when
            Score actual = Score.calculateSumFrom(hand);
            Score expected = Score.valueOf(22);

            // then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
