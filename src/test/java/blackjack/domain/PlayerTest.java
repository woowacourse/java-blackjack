package blackjack.domain;

import blackjack.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @DisplayName("플레이어 생성")
    @Test
    void create() {
        Player root = new Player("root");
        assertThat(root).isEqualTo(new Player("root"));
    }

    @DisplayName("이름이 공백인 경우 검증")
    @Test
    void validate() {
        assertThatThrownBy(() -> new Player(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백은 이름으로 사용할 수 없습니다.");
    }
}
