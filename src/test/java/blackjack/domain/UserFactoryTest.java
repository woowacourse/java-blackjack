package blackjack.domain;

import blackjack.utils.NameParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserFactoryTest {
    @Test
    @DisplayName("선수 이름이 8명을 초과하여 입력됐을 때 exception ")
    void moreThanMaxPlayerNumber() {
        assertThatThrownBy(() -> UserFactory.generateUsers(NameParser.parse("s, ab,cd,11,14,sdf,cu,lkl,hih")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초과");
    }
}
