package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HitOrStandTest {

    @Nested
    class FromTest {

        @Nested
        class Success {

            @Test
            void y를_입력하면_HIT을_반환한다() {
                // given
                String input = "y";

                // when
                HitOrStand actual = HitOrStand.from(input);

                // then
                assertThat(actual).isEqualTo(HitOrStand.HIT);
            }

            @Test
            void n을_입력하면_STAND를_반환한다() {
                // given
                String input = "n";

                // when
                HitOrStand actual = HitOrStand.from(input);

                // then
                assertThat(actual).isEqualTo(HitOrStand.STAND);
            }

            @Test
            void 앞뒤_공백이_있어도_정상_처리한다() {
                // given
                String input = " y ";

                // when
                HitOrStand actual = HitOrStand.from(input);

                // then
                assertThat(actual).isEqualTo(HitOrStand.HIT);
            }
        }

        @Nested
        class Fail {

            @Test
            void y_또는_n가_아니면_예외를_던진다() {
                // given
                String input = "a";

                // when & then
                assertThatThrownBy(() -> HitOrStand.from(input))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(HitOrStand.INVALID_YES_NO_INPUT);
            }
        }
    }

    @Nested
    class IsHitTest {

        @Test
        void HIT이면_true를_반환한다() {
            // given
            HitOrStand hitOrStand = HitOrStand.HIT;

            // when
            boolean actual = hitOrStand.isHit();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void STAND이면_false를_반환한다() {
            // given
            HitOrStand hitOrStand = HitOrStand.STAND;

            // when
            boolean actual = hitOrStand.isHit();

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class IsStandTest {

        @Test
        void STAND이면_true를_반환한다() {
            // given
            HitOrStand hitOrStand = HitOrStand.STAND;

            // when
            boolean actual = hitOrStand.isStand();

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void HIT이면_false를_반환한다() {
            // given
            HitOrStand hitOrStand = HitOrStand.HIT;

            // when
            boolean actual = hitOrStand.isStand();

            // then
            assertThat(actual).isFalse();
        }
    }
}
