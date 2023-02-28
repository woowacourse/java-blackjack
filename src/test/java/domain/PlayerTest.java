package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("플레이어는 ")
class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"가비", "열_글자_입니다..", "공백도 포함합니다."})
    void 이름은_공백을_제외하고_2에서_10글자이다(String name) {
        //given

        //when

        //then
        assertDoesNotThrow(() -> new Player(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "abcdqwertyuiopdfghjk"})
    void 이름은_공백을_제외하고_2에서_10글자가_아니면_예외가_발생한다(String name) {
        //given

        //when

        //then
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.");
    }
}