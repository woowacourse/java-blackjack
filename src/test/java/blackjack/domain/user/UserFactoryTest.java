package blackjack.domain.user;

import blackjack.utils.InputHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserFactoryTest {
    @Test
    @DisplayName("선수 이름이 8명을 초과하여 입력됐을 때 exception ")
    void moreThanMaxPlayerNumber() {
        assertThatThrownBy(() -> UserFactory.generateUsers(InputHandler.parseName("s, ab,cd,11,14,sdf,cu,lkl,hih")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("8명 이하");
    }

    @Test
    @DisplayName("선수 이름이 1명 미만으로 입력됐을 때 exception ")
    void lessThanMinPlayerNumber() {
        assertThatThrownBy(() -> UserFactory.generateUsers(InputHandler.parseName("s,")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("2명 이상");
    }
}
