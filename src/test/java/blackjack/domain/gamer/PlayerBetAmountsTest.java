package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetAmountsTest {

    @Test
    @DisplayName("플레이어는 2명부터 8명까지 허용된다.")
    void validPlayerNamesTest() {
        assertThatCode(() -> createFromNames(List.of("a", "b")))
                .doesNotThrowAnyException();
        assertThatCode(() -> createFromNames(List.of("a", "b", "c", "d", "e", "f", "g", "h")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어는 적어도 2명 이상이어야 한다.")
    void minimumPlayerCountTest() {
        assertThatThrownBy(() -> createFromNames(List.of("a")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 2명에서 최대 8명까지 가능합니다.");
    }

    @Test
    @DisplayName("플레이어는 최대 8명까지만 가능하다.")
    void maximumPlayerCountTest() {
        assertThatThrownBy(() -> createFromNames(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최소 2명에서 최대 8명까지 가능합니다.");
    }

    private PlayerBetAmounts createFromNames(List<String> names) {
        Map<Player, Money> playerBetAmountMap = new LinkedHashMap<>();

        for (String name : names) {
            Player player = new Player(name);
            playerBetAmountMap.put(player, Money.getZeroAmountMoney());
        }

        return new PlayerBetAmounts(playerBetAmountMap);
    }
}
