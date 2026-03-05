package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.dto.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestScorer {

    @Test
    public void 일반_카드_변환_정상_작동() {
        //given
        Card card = new Card(Shape.CLOVER,CardNumber.EIGHT);

        //then
        assertThat(Scorer.calculate(card)).isEqualTo(card.cardNumber().getScore());
    }

    @ParameterizedTest
    @CsvSource({"11, 1", "10, 11"})
    public void A_카드_변환_정상_작동(int input, int result) {
        Card card = new Card(Shape.CLOVER,CardNumber.ACE);

        //then
        assertThat(Scorer.calculate(card, input)).isEqualTo(result);

    }

}
