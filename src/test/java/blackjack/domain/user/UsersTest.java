package blackjack.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class UsersTest {
    private List<String> names;

    @BeforeEach
    void setUp() {
        names = Arrays.asList("pobi", "brown", "jason");
    }

    @DisplayName("Users가 정상적으로 생성되는 지 테스트")
    @Test
    void generate_test() {
        assertThatCode(() -> new Users(names)).doesNotThrowAnyException();
    }

    @DisplayName("Users 내부 필드로 딜러와 플레이어를 가진다.")
    @Test
    void generate_test2() {
        Users users = new Users(names);
        assertThat(users.getDealer()).isEqualTo(new Dealer());
        for (String name : names) {
            assertThat(users.getPlayers()).contains(new Player(name));
        }
    }
}