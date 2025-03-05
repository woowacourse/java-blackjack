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

    @Test
    void _카드_합이_항상_21을_초과할_경우_TRUE를_반환한다() {
        // given
        Cards cards = new Cards(List.of(
                new Card(CardShape.SPADE, CardNumber.JACK),
                new Card(CardShape.SPADE, CardNumber.QUEEN),
                new Card(CardShape.SPADE, CardNumber.TWO),
                new Card(CardShape.SPADE, CardNumber.ACE)
        ));

        // when
        final boolean isBurst = cards.isBurst();

        // then
        assertThat(isBurst).isTrue();
    }

    @Test
    void _카드_합을_21_이하로_만들_수_있는_경우_FALSE를_반환한다() {
        // given
        Cards cards = new Cards(List.of(
                new Card(CardShape.SPADE, CardNumber.QUEEN),
                new Card(CardShape.SPADE, CardNumber.TWO),
                new Card(CardShape.SPADE, CardNumber.ACE)
        ));

        // when
        final boolean isBurst = cards.isBurst();

        // then
        assertThat(isBurst).isFalse();
    }
}
