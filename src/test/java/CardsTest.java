import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardNumber;
import domain.CardShape;
import domain.Cards;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardsTest {
    @Test
    void 가진_카드_목록으로_21을_초과하지_않는_최대합_계산() {
        // given
        Cards cards = new Cards(List.of(
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.SPADE, CardNumber.THREE),
                new Card(CardShape.SPADE, CardNumber.FOUR),
                new Card(CardShape.SPADE, CardNumber.JACK)
        ));
        final int expected = 19;

        // when
        int sum = cards.calculateOptimalSum();

        // then
        assertThat(sum).isEqualTo(expected);
    }
}
