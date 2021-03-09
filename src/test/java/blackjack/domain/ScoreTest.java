package blackjack.domain;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    @DisplayName("Cards 객체를 받아 점수를 반환한다.")
    void calculatorScore() {
        Cards cards1 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.TWO),
                new Card(Shape.HEART, Denomination.ACE)
                ));

        Cards cards2 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.JACK)
        ));

        Cards cards3 = new Cards(Arrays.asList(
                new Card(Shape.SPADE, Denomination.ACE),
                new Card(Shape.SPADE, Denomination.EIGHT),
                new Card(Shape.HEART, Denomination.ACE)
        ));

        assertThat(Score.calculatorScore(cards1)).isEqualTo(14);
        assertThat(Score.calculatorScore(cards2)).isEqualTo(21);
        assertThat(Score.calculatorScore(cards3)).isEqualTo(20);
    }
}
