package constant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerActionTest {

    @Nested
    class FromTest {

        @Nested
        class Success {

            @Test
            void 입력값이_y면_HIT를_생성한다() {

                // given
                String input = "y";

                // when
                PlayerAction actual = PlayerAction.from(input);

                // then
                Assertions.assertThat(actual).isEqualTo(PlayerAction.HIT);
            }

            @Test
            void 입력값이_n면_STAND를_생성한다() {

                // given
                String input = "n";

                // when
                PlayerAction actual = PlayerAction.from(input);

                // then
                Assertions.assertThat(actual).isEqualTo(PlayerAction.STAND);
            }
        }

        @Nested
        class Fail {

            @Test
            void 입력값이_y_또는_n이_아니라면_예외를_발생_시켜야한다() {

                // given
                String input = "yn";

                // when & then
                assertThatThrownBy(() -> {
                    PlayerAction.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.INVALID_YES_NO_INPUT.getMessage());
            }
        }
    }
}