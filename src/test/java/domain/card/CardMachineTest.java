package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardMachineTest {

    private final CardMachine cardMachine = new CardMachine();

    @Nested
    class DrawCardTest {

        @Nested
        class Success {

            @Test
            void 카드를_뽑으면_null이_아닌_Card를_반환해야_한다() {

                // when
                Card actual = cardMachine.drawCard();

                // then
                assertThat(actual).isNotNull();
            }

            @Test
            void 카드를_총_312장_뽑을_수_있고_각_카드는_최대_6장까지_나와야_한다() {

                // given
                Map<Card, Integer> counts = new HashMap<>();

                // when
                for (int i = 0; i < 312; i++) {
                    Card drawn = cardMachine.drawCard();
                    counts.put(drawn, counts.getOrDefault(drawn, 0) + 1);
                }

                // then
                assertThat(counts).hasSize(52);
                assertThat(counts.values()).allMatch(count -> count <= 6);
                assertThat(counts.values()).allMatch(count -> count == 6);
            }

            @Test
            void 카드가_모두_소진되면_예외를_발생_시켜야_한다() {

                // given
                for (int i = 0; i < 312; i++) {
                    cardMachine.drawCard();
                }

                // when & then
                assertThatThrownBy(cardMachine::drawCard)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NO_CARDS_LEFT_TO_DRAW.getMessage());
            }
        }
    }
}
