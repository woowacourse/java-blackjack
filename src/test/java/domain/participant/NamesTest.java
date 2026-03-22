package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Nested
    class FromTest {

        @Nested
        class Success {

            @Test
            void 사용자_입력값으로_Names가_정상_생성_된다() {

                // given
                String input = "jacob, pobi";

                // when
                Names actual = Names.from(input);

                // then
                Assertions.assertThat(actual.value().size()).isEqualTo(2);
                Assertions.assertThat(actual.value().get(0)).isEqualTo(new Name("jacob"));
                Assertions.assertThat(actual.value().get(1)).isEqualTo(new Name("pobi"));
            }
        }

        @Nested
        class Fail {

            @Test
            void 이름이_중복_되면_예외를_발생시켜야_한다() {

                // given
                String input = "jacob, jacob";

                // when & then
                assertThatThrownBy(() -> {
                    Names.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.NAME_DUPLICATED.getMessage());
            }

            @Test
            void 이름이_2개_미만이라면_예외를_발생시켜야_한다() {

                // given
                String input = "jacob";

                // when & then
                assertThatThrownBy(() -> {
                    Names.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
            }

            @Test
            void 이름이_8개_초과라면_예외를_발생시켜야_한다() {

                // given
                String input = "ja0, ja1, ja2, ja3, ja4, ja5, ja6, ja7, ja8";

                // when & then
                assertThatThrownBy(() -> {
                    Names.from(input);
                })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE.getMessage());
            }
        }
    }
}