package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsersTest {
    @DisplayName("유저 이름이 중복되면 에러가 검출되는지 확인")
    @Test
    void whenDuplicateNames() {
        List<User> duplicateUsers = Arrays.asList(
                new User("욘", 0),
                new User("웨지", 0),
                new User("포비", 0),
                new User("욘", 0));

        assertThatThrownBy(() -> new Users(duplicateUsers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 존재할 수 없습니다.");
    }
}