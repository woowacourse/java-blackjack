package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserDrawContinueTest {
    @DisplayName("추가로 카드 받기 질문에 대해 사용자가 y를 입력했을 때")
    @Test
    void userInputDrawContinue() {
        String input = "y";
        UserDrawContinue userDrawContinue = new UserDrawContinue(input);
        assertThat(userDrawContinue.isContinue()).isTrue();
    }

    @DisplayName("추가로 카드 받기 질문에 대해 사용자가 n를 입력했을 때")
    @Test
    void userInputNotDrawContinue() {
        String input = "n";
        UserDrawContinue userDrawContinue = new UserDrawContinue(input);
        assertThat(userDrawContinue.isContinue()).isFalse();
    }

    @DisplayName("추가로 카드 받기 질문에 대해 사용자가 y, n 외의 문자를 입력했을 때")
    @ParameterizedTest
    @ValueSource(strings = {"a", "\n", " ", "\t", "Y", "N"})
    void userInputNotValidException(String input) {
        assertThatThrownBy(() -> new UserDrawContinue(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
