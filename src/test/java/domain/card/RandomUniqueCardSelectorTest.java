package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomUniqueCardSelectorTest {

    @Nested
    @DisplayName("selectCardOrder() 테스트")
    class SelectCardOrderMethodTest {

        @RepeatedTest(10)
        @DisplayName("카드의 총 개수를 건네주면 카드의 순서를 반환한다")
        void selectCardOrder_givenCardSize_thenReturnCardOrder() {
            // given
            final CardSelector cardSelector = new RandomUniqueCardSelector();

            // when
            int actual = cardSelector.selectCardOrder(52);

            // then
            assertThat(actual)
                    .isLessThan(52)
                    .isGreaterThanOrEqualTo(0);
        }

        @Test
        @DisplayName("selectCardOrder()는 카드의 총 개수를 건네주면 고유한 카드의 순서를 반환한다")
        void selectCardOrder_givenCardSize_thenReturnUniqueCardOrder() {
            // given
            final CardSelector cardSelector = new RandomUniqueCardSelector();
            final Set<Integer> cardOrder = new HashSet<>();
            int count = 0;

            // when
            while (count++ < 52) {
                int randomCardOrder = cardSelector.selectCardOrder(52);

                cardOrder.add(randomCardOrder);
            }

            // then
            assertThat(cardOrder.size())
                    .isSameAs(52);
        }
    }
}
