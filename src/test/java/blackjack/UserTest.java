package blackjack;

import blackjack.domain.User;
import blackjack.exception.UserNameEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    @DisplayName("user 생성 시 빈 문자열일 경우 예외 발생 확인")
    @Test
    void emptyStringTest() {
        assertThatThrownBy(() -> {
            new User("");
        }).isInstanceOf(UserNameEmptyException.class)
                .hasMessage("유저의 이름은 공백일 수 없습니다.");
    }
}
