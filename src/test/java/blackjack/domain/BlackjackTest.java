package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BlackjackTest {

    @Test
    @DisplayName("7개 이상의 이름을 입력한 경우 예외가 발생한다.")
    void tooManyPlayers() {
        String[] names = {"a", "b", "c", "d", "e", "f", "g", "h"};
        assertThatThrownBy(() -> new Blackjack(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최대")
                .hasMessageContaining("명까지 플레이 가능합니다.");
    }

    @Test
    @DisplayName("중복되는 이름을 입력한 경우 예외가 발생한다.")
    void duplicatedName() {
        String[] names = {"pobi", "pobi"};
        assertThatThrownBy(() -> new Blackjack(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복되는 이름은 허용되지 않습니다.");
    }
}
