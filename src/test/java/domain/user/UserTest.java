package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("이름을 가진다.")
    void hasNameTest() {
        Name name = new Name("poby");
        Player player = new Player(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("유저가 플레이어인지 확인한다")
    void isPlayerTest() {
        User player = new Player(new Name("a"));
        User dealer = new Dealer();
        assertThat(player.isPlayer()).isTrue();
        assertThat(dealer.isPlayer()).isFalse();
    }
}
