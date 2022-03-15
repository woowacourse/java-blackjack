package blackjack.domain.player;

import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.player.PlayerArgumentsProvider;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

class PlayersTest {

    @DisplayName("플레이어가 1명 이상인지 확인한다.")
    @ParameterizedTest
    @ArgumentsSource(PlayerArgumentsProvider.class)
    void 플레이어_1명이상_확인_정상(List<Player> players) {
        assertDoesNotThrow(() -> new Players(players));
    }

    @DisplayName("1명 이하일 때 에러")
    @Test
    void 플레이어_Empty() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Players(List.of()));
    }
}