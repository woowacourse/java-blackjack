package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    @DisplayName("Players가 잘 생성되는지 확인")
    void create() {
        final List<String> names = new ArrayList<>(Arrays.asList("bada", "pobi"));
        assertThatCode(() -> new Players(names))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복 이름이 들어올 때 익셉션을 날리는지 확인")
    void validateSameName() {
        final List<String> names = new ArrayList<>(Arrays.asList("bada", "bada"));
        assertThatThrownBy(() -> new Players(names)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Players.SAME_NAME_ERROR);
    }
}
