package blackjack.domain.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UsersTest {
    private List<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>(Arrays.asList("pobi", "brown", "jason"));
    }

    @DisplayName("Users가 정상적으로 생성되는 지 테스트")
    @Test
    void generate_test() {
        assertThatCode(() -> Users.of(names)).doesNotThrowAnyException();
    }

    @DisplayName("Users 내부 필드로 딜러와 플레이어를 가진다.")
    @Test
    void generate_test2() {
        Users users = Users.of(names);
        assertThat(users.getDealer()).isEqualTo(new Dealer());
        for (String name : names) {
            assertThat(users.getPlayers()).contains(new Player(name));
        }
    }

    @DisplayName("Player가 중복된 이름을 가지는 경우 예외처리해준다.")
    @Test
    void duplicate_players_test() {
        names = new ArrayList<>(Arrays.asList("pobi", "brown", "brown", "jason"));
        assertThatThrownBy(() -> Users.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저 이름이 중복됩니다!");

    }

    @DisplayName("Player 이름이 '딜러'인 경우 예외처리해준다.")
    @Test
    void duplicate_players_test2() {
        names = new ArrayList<>(Arrays.asList("pobi", "brown", "딜러", "jason"));
        assertThatThrownBy(() -> Users.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저 이름이 중복됩니다!");

    }
}