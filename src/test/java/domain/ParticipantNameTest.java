package domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import common.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantNameTest {
    @Test
    @DisplayName("공백은 이름으로 쓸 수 없다")
    void not_allow_empty() {
        //given
        String blank = " ";

        //when, then
        assertThatThrownBy(
                () -> new ParticipantName(blank)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_ALLOW_EMPTY_INPUT.getMessage());
    }

    @Test
    @DisplayName("특수문자, 숫자는 이름에 포함될 수 없다")
    void not_allow_special_character() {
        //given
        String nameWithSpecialChar = "asdf_123";

        //when, then
        assertThatThrownBy(
                () -> new ParticipantName(nameWithSpecialChar)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ONLY_KO_AND_ENG.getMessage());
    }

    @Test
    @DisplayName("한국어,영어로 이루어진 이름은 좋다")
    void name_success() {
        //given
        String name = "rati";

        //when, then
        Assertions.assertDoesNotThrow(
                () -> new ParticipantName(name)
        );
    }
}