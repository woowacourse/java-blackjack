package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.NeedRetryException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNamesTest {

    @DisplayName("중복된 이름의 참여자를 생성할 수 없다.")
    @Test
    void validateDuplicateName() {
        String name = "pobi";
        final List<PlayerName> playerNames = List.of(new PlayerName(name), new PlayerName(name));

        assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(NeedRetryException.class);
    }

    @DisplayName("딜러 이름으로 참여자를 생성할 수 없다.")
    @Test
    void validateDealerName() {
        final List<PlayerName> playerNames = List.of(new PlayerName("pobi"), new PlayerName("딜러"));

        assertThatThrownBy(() -> new PlayerNames(playerNames))
                .isInstanceOf(NeedRetryException.class);
    }
}
