package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    @DisplayName("이름을 가진다.")
    void hasNameTest() {
        Name name = new Name("poby");
        Player player = new Player(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("플레이어면 true를 반환한다.")
    void isPlayer() {
        User player = new Player(new Name("poby"));

        assertThat(player.isPlayer()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 아니면 false를 반환한다.")
    void isNotPlayer() {
        User dealer = new Dealer();

        assertThat(dealer.isPlayer()).isFalse();
    }
}
