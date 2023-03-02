package model;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    @Test
    @DisplayName("두 명의 이름이 주어지면 딜러를 포함해서 3명의 Players 반환된다.")
    void createDealer() {
        // given, when
        final Users users = new Users(List.of("bebe", "ethan"));

        // then
        assertThat(users)
                .extracting("users", InstanceOfAssertFactories.list(User.class))
                .containsExactly(Dealer.getInstance(), new Player("bebe"), new Player("ethan"));
    }
}
