package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserNamesTest {

    @DisplayName("중복된 이름의 참여자를 생성할 수 없다.")
    @Test
    void validateDuplicateName() {
        String name = "pobi";
        final List<UserName> userNames = List.of(new UserName(name), new UserName(name));

        assertThatThrownBy(() -> new PlayerNames(userNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러 이름으로 참여자를 생성할 수 없다.")
    @Test
    void validateDealerName() {
        final List<UserName> userNames = List.of(new UserName("pobi"), new UserName("딜러"));

        assertThatThrownBy(() -> new PlayerNames(userNames))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
