package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"j", "jjjjjj", "jj jjj"})
    void 게임_참가자_이름의_길이가_2_이상_5_이하가_아니라면_예외를_발생_시켜야_한다(String input) {

        // when & then
        assertThatThrownBy(() -> {
            new Name(input);
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.PLAYER_NAME_LENGTH_OUT_OF_RANGE.getMessage());
    }

}