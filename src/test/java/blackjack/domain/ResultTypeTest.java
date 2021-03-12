package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Betting;
import blackjack.domain.participants.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    @Test
    @DisplayName("값 차이에 따른 게임 결과 테스트")
    void getResultType() {
        Player eighteen = new Player(new Name("eighteen"), Betting.valueOf("1"));
        eighteen.draw(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT)
        );
        Player nineteen = new Player(new Name("nineteen"), Betting.valueOf("1"));
        nineteen.draw(
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT)
        );
        Player seventeen = new Player(new Name("seventeen"), Betting.valueOf("1"));
        seventeen.draw(
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.SEVEN)
        );

        Player anotherEighteen = new Player(new Name("anotherEighteen"), Betting.valueOf("1"));
        anotherEighteen.draw(
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT)
        );

        assertThat(ResultType.getResultTypeByScore(nineteen, eighteen)).isEqualTo(ResultType.WIN);
        assertThat(ResultType.getResultTypeByScore(eighteen, anotherEighteen))
            .isEqualTo(ResultType.TIE);
        assertThat(ResultType.getResultTypeByScore(seventeen, eighteen)).isEqualTo(ResultType.LOSE);
    }
}
