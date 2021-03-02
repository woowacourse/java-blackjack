package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @DisplayName("User 객체를 생성한다.")
    @Test
    public void createUser() {
        User user = new User();

        assertThat(user).isInstanceOf(User.class);
    }
}
