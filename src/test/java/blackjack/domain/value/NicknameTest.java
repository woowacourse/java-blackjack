package blackjack.domain.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.value.Nickname;
import blackjack.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NicknameTest {

    @ParameterizedTest
    @DisplayName("입력된 닉네임의 앞뒤 공백을 제거한다.")
    @ValueSource(strings = {"  쿠키", "쿠키   "})
    void removeSideSpace(String input) {
        Nickname nickname = new Nickname(input);
        assertThat(nickname.getValue()).isEqualTo("쿠키");
    }

    @ParameterizedTest
    @DisplayName("닉네임은 공백을 허용하지 않는다.")
    @NullAndEmptySource
    void nicknameIsShouldNotBlank(String input) {
        assertThatThrownBy(() -> new Nickname(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_ALLOWED_EMPTY_NICKNAME.getContent());
    }
}
