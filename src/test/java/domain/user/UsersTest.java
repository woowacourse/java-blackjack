package domain.user;

import factory.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest {
    @Test
    @DisplayName("Users 생성")
    void create() {
        assertThat(new Users(UserFactory.create("playerA,playerB"))).isInstanceOf(Users.class);
    }
}