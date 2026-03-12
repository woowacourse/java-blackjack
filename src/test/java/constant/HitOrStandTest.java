package constant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HitOrStandTest {

    @Nested
    class From {

        @Nested
        class Fail {

            @Test
            void y_또는_n가_아니면_예외가_발생한다() {
                // given
                String input = "a";

                // when & then
                assertThatThrownBy(() -> HitOrStand.from(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(HitOrStand.INVALID_YES_NO_INPUT);
            }
        }
    }
}