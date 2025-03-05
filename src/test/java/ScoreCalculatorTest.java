import java.util.List;
import model.Card;
import model.CardNumber;
import model.CardType;
import model.Cards;
import model.ScoreCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {

    @Test
    @DisplayName("에이스가 없는 경우의 수를 구할 수 있다.")
    void test0() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Card card1 = new Card(CardType.SPADE, CardNumber.THREE);
        Card card2 = new Card(CardType.SPADE, CardNumber.FIVE);
        Card card3 = new Card(CardType.SPADE, CardNumber.QUEEN);

        // when
        List<Integer> result = scoreCalculator.calculate(new Cards(List.of(
                card1, card2, card3
        )));

        // then
        Assertions.assertThat(result)
                .contains(18);
    }

    @Test
    @DisplayName("에이스가 1장 있을 때 경우의 수를 계산할 수 있다.")
    void test() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Card card1 = new Card(CardType.SPADE, CardNumber.THREE);
        Card card2 = new Card(CardType.SPADE, CardNumber.ACE);

        // when
        List<Integer> result = scoreCalculator.calculate(new Cards(List.of(
                card1, card2
        )));

        // then
        Assertions.assertThat(result)
                .contains(4, 14);
    }

    @Test
    @DisplayName("에이스가 2장 있을 때 경우의 수를 계산할 수 있다.")
    void test1() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Card card1 = new Card(CardType.SPADE, CardNumber.THREE);
        Card card2 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardType.SPADE, CardNumber.ACE);

        // when
        List<Integer> result = scoreCalculator.calculate(new Cards(List.of(
                card1, card2, card3
        )));

        // then
        Assertions.assertThat(result)
                .contains(5, 15, 25);
    }

    @Test
    @DisplayName("에이스가 3장 있을 때 경우의 수를 계산할 수 있다.")
    void test2() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Card card1 = new Card(CardType.SPADE, CardNumber.THREE);
        Card card2 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card4 = new Card(CardType.SPADE, CardNumber.ACE);

        // when
        List<Integer> result = scoreCalculator.calculate(new Cards(List.of(
                card1, card2, card3, card4
        )));

        // then
        Assertions.assertThat(result)
                .contains(6, 16, 26, 36);
    }

    @Test
    @DisplayName("에이스가 4장 있을 때 경우의 수를 계산할 수 있다.")
    void test4() {
        // given
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        Card card1 = new Card(CardType.SPADE, CardNumber.THREE);
        Card card2 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card4 = new Card(CardType.SPADE, CardNumber.ACE);
        Card card5 = new Card(CardType.SPADE, CardNumber.ACE);

        // when
        List<Integer> result = scoreCalculator.calculate(new Cards(List.of(
                card1, card2, card3, card4, card5
        )));

        // then
        Assertions.assertThat(result)
                .contains(7, 17, 27, 37, 47);
    }
}
