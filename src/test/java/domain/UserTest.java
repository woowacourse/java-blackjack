package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.player.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void 참여자_이름은_2자_이상이어야_한다() {
        // given
        String name = "a";

        // when & then
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_이름은_10자_이하여야_한다() {
        // given
        String name = "12345678901";

        // when & then
        assertThatThrownBy(() -> new User(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
