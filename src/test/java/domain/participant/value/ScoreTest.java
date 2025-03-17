package domain.participant.value;

import static org.assertj.core.api.Assertions.*;

import card.Card;
import card.CardNumberType;
import card.CardShapeType;
import card.Hand;
import participant.value.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @DisplayName("카드의 합이 21 초과라면 true를 반환한다")
    @Test
    void test1() {
        //given
        Score score = new Score(22);

        //when & then
        assertThat(score.isBust()).isTrue();
    }

    @DisplayName("카드의 합이 21 이하라면 false를 반환한다")
    @Test
    void test8() {
        //given
        Score score = new Score(21);

        //when & then
        assertThat(score.isBust()).isFalse();
    }

    @DisplayName("카드의 합을 구한다")
    @Test
    void test5() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.SIX, CardShapeType.CLOVER),
                new Card(CardNumberType.JACK, CardShapeType.DIAMOND));

        //when
        Score score = Score.from(testCards);

        //then
        assertThat(score).isEqualTo(new Score(16));
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 11 이상이면 ACE를 1로 간주한다")
    @Test
    void test10() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardShapeType.CLOVER),
                new Card(CardNumberType.SIX, CardShapeType.DIAMOND),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND)
        );

        //when
        Score score = Score.from(testCards);

        //then
        assertThat(score).isEqualTo(new Score(12));
    }

    @DisplayName("만약 ACE가 포함된 경우, 나머지 카드의 합이 10 이하이면 ACE를 11으로 간주한다")
    @Test
    void test6() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.FIVE, CardShapeType.CLOVER),
                new Card(CardNumberType.FIVE, CardShapeType.DIAMOND),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND)
        );

        //when
        Score score = Score.from(testCards);

        //then
        assertThat(score).isEqualTo(new Score(21));
    }

    @DisplayName("ACE가 여러 개인 경우, 각 ACE를 최적의 값으로 포함하여 총합을 계산한다")
    @Test
    void test7() {
        //given
        List<Card> testCards = List.of(new Card(CardNumberType.ACE, CardShapeType.CLOVER),
                new Card(CardNumberType.ACE, CardShapeType.HEART),
                new Card(CardNumberType.ACE, CardShapeType.DIAMOND)
        );
        Hand hand = new Hand(testCards);

        //when
        Score score = Score.from(testCards);

        //then
        assertThat(score).isEqualTo(new Score(13));
    }

}
