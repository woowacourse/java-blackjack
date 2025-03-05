package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @DisplayName("각 플레이어 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        // given
        Player player = new Player("두리");

        // when
        int result = player.getCards().size();

        // then
        assertThat(result).isEqualTo(2);
    }
}
