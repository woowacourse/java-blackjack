package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("참가자의 이름이 중복되면 예외가 발생한다.")
    void nameDuplicate() {
        // give
        String name = "rick";

        // when
        final List<String> names = List.of(name, name);

        // then
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복을 허용하지 않습니다.");
    }

    @Test
    @DisplayName("참가자가 2명 미만이면 예외를 던진다.")
    void playerLowerBound() {
        // give
        final List<String> names = List.of("rick");

        // when
        // then
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("2명 이상의 참가자가 필요합니다.");
    }

    @Test
    @DisplayName("참가자가 2명 미만이면 예외를 던진다.")
    void playerUpperBound() {
        // give
        final List<String> names = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

        // when
        // then
        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("8명 까지만 참여할 수 있습니다.");
    }
}