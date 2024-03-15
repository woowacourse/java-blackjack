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

        assertThat(player.getName()).isEqualTo("poby");
    }

}
