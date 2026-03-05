package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Nested
    class ConstructorTest {

        @Nested
        class Success {

            @Test
            void 게임_참가자_조건이_맞으면_성공_이다() {

                // given
                String name = "jacob";

                // when
                Player player = new Player(name);
                String actual = player.getName();

                // then
                String expected = "jacob";
                Assertions.assertEquals(expected, actual);
            }
            
        }

        @Nested
        class Fail {

            @ParameterizedTest
            @ValueSource(strings = {"a", "aaa aa"})
            void 게임_참가자_이름의_길이가_2_이상_5_이하가_아니라면_예외를_발생_시켜야_한다(String name) {

                // when & then
                assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 게임 참가자의 이름은 2~5글자 사이여야 합니다.");
            }

            @Test
            void 입력값에_공백만_입력되면_예외를_발생_시켜야_한다() {

                // given
                String name = "  ";

                // when & then
                assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR] 게임 참가자의 이름은 공백이 될 수 없습니다.");
            }
        }
    }
}
