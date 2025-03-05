package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NicknameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("닉네임은 공백을 허용하지 않는다.")
    void nicknameIsShouldNotBlank(String input) {
        assertThatThrownBy(() -> new Nickname(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 공백을 허용하지 않습니다.");
    }
}
