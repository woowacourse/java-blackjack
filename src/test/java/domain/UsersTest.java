package domain;

import domain.player.User;
import domain.player.Users;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersTest {

    @Test
    void 참여자_인원이_0명인_경우_예외가_발생한다() {
        // given
        List<User> users = new ArrayList<>();

        // when & then
        Assertions.assertThatThrownBy(() -> new Users(users))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참여자_인원이_5인_초과인_경우_예외가_발생한다() {
        // given
        List<User> users = List.of(
                new User("시소"),
                new User("헤일러"),
                new User("부기"),
                new User("사나"),
                new User("수양"),
                new User("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Users(users))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 플레이어_이름이_중복될_경우_예외가_발생한다() {
        // given

        List<User> users = List.of(
                new User("시소"),
                new User("헤일러"),
                new User("사나"),
                new User("사나"),
                new User("히스타"),
                new User("포스티")
        );

        // when & then
        Assertions.assertThatThrownBy(() -> new Users(users))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
