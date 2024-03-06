package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("참여자 이름은 한 글자 이상이 아니면 예외가 발생한다")
    void validatePlayerNameLengthTest() {
        // when & then
        assertThatThrownBy(() -> new Player("", () -> 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("21을 넘지 않을 경우 얼마든지 카드를 계속 뽑을 수 있다")
    void canHitTest() {
        // given
        Player player = new Player("dora", new SequentialNumberGenerator(List.of(0, 9, 0, 9)));

        // when & then
        assertThat(player.canHit()).isTrue();
    }
}
