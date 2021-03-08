package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    @Test
    @DisplayName("값 차이에 따른 게임 결과 테스트")
    void getResultType() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.SEVEN),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT)));

        Player eighteen = new Player("eighteen", deck);
        Player nineteen = new Player("nineteen", deck);
        Player seventeen = new Player("seventeen", deck);
        Player anotherEighteen = new Player("anotherEighteen", deck);

        assertThat(ResultType.getResultTypeByScore(nineteen, eighteen)).isEqualTo(ResultType.WIN);
        assertThat(ResultType.getResultTypeByScore(eighteen, anotherEighteen)).isEqualTo(ResultType.TIE);
        assertThat(ResultType.getResultTypeByScore(seventeen, eighteen)).isEqualTo(ResultType.LOSE);
    }
}
