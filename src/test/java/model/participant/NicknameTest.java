package model.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import participant.Nickname;

class NicknameTest {

    @Test
    @DisplayName("닉네임 객체를 생성이 잘 되는지")
    void newNickname() {

        // given
        final String valueInput = "pobi";
        final String expected = "pobi";

        // when
        final Nickname nickname = new Nickname(valueInput);

        // then
        Assertions.assertThat(nickname.getValue()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "abcdefghijk"})
    @DisplayName("유효하지 않은 닉네임 길이일 때 예외처리")
    void validateLength(String value) {
        Assertions.assertThatThrownBy(() -> new Nickname(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
