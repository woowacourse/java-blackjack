package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsersTest {
    @DisplayName("유저 이름이 중복되는 경우 예외를 발생한다.")
    @Test
    void testValidateUsersName() {
        List<Name> names = Arrays.asList(Name.from("pobi"), Name.from("pobi"));

        assertThatThrownBy(() -> Users.from(names)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되는 이름은 존재할 수 없습니다.");
    }
}