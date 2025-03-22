package model.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import setupSettings.NicknameGenerator;

class NicknameTest {

    @Test
    @DisplayName("정규표현식이 적절한 지")
    void prefix() {
        // given
        String prefix = "^[a-z]{2,10}$";
        // when
        String nickname = "pobi";
        boolean result = nickname.matches(prefix);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("닉네임 객체를 생성이 잘 되는지")
    void newNickname() {

        // given
        final String nicknameData = "pobi";

        // when
        final Nickname nickname = NicknameGenerator.generateNicknameBy(nicknameData);

        // then
        Assertions.assertThat(nickname.getValue()).isEqualTo(nicknameData);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a","abcdefghijk"})
    @DisplayName("유효하지 않은 닉네임 길이일 때 예외처리")
    void validateLength(String value) {
        Assertions.assertThatThrownBy(() -> NicknameGenerator.generateNicknameBy(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
