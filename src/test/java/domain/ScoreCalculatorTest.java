package domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculatorTest {
    @Test
    void 점수_계산_테스트() {
        List<Card> cards = List.of(
                Card.of(CardNumber.J, CardShape.CLOVER),
                Card.of(CardNumber.Q, CardShape.CLOVER));
        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(20);
    }

    @Test
    void ACE_카드_11_계산_로직_확인_테스트() {
        List<Card> cards = List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.Q, CardShape.CLOVER));
        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(21);
    }

    @Test
    void ACE_카드만_두_장일_경우_카드_총합_확인_테스트() {
        List<Card> twoAceCard = List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.ACE, CardShape.HEART));

        assertThat(ScoreCalculator.calculate(twoAceCard)).isEqualTo(12);
    }

    @Test
    void ACE_카드_세_장일_경우_카드_총합_확인_테스트() {
        List<Card> cards = List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.ACE, CardShape.HEART),
                Card.of(CardNumber.ACE, CardShape.DIAMOND),
                Card.of(CardNumber.EIGHT, CardShape.DIAMOND));

        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(21);
    }

    @Test
    void ACE_카드만_한_장과_그_외의_합이_20일_경우_카드_총합_확인_테스트() {
        List<Card> cards = List.of(
                Card.of(CardNumber.ACE, CardShape.CLOVER),
                Card.of(CardNumber.Q, CardShape.HEART),
                Card.of(CardNumber.J, CardShape.HEART));

        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(21);
    }

    @Test
    void 카드가_없을_경우_0_확인_테스트() {
        List<Card> twoAceCard = List.of();
        assertThat(ScoreCalculator.calculate(twoAceCard)).isEqualTo(0);
    }

    @Test
    void 카드의_합_21_초과_반환_테스트() {
        List<Card> cards = List.of(
                Card.of(CardNumber.K, CardShape.CLOVER),
                Card.of(CardNumber.Q, CardShape.HEART),
                Card.of(CardNumber.J, CardShape.HEART));

        assertThat(ScoreCalculator.calculate(cards)).isEqualTo(30);
    }
}
