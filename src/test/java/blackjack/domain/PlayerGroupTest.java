package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerGroupTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("플레이어그룹 생성 오류 테스트")
    void playerInvalidTest(List<Player> players, String testName) {
        assertThatThrownBy(() -> new PlayerGroup(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(List.of(), "플레이어 0명 입력"),
                Arguments.of(List.of(Player.of("a", initializeBettingMoney()),
                        Player.of("b", initializeBettingMoney()),
                        Player.of("c", initializeBettingMoney()),
                        Player.of("d", initializeBettingMoney()),
                        Player.of("e", initializeBettingMoney()),
                        Player.of("f", initializeBettingMoney()),
                        Player.of("g", initializeBettingMoney()),
                        Player.of("h", initializeBettingMoney()),
                        Player.of("i", initializeBettingMoney())), "플레이어 9명 입력"),
                Arguments.of(List.of(Player.of("pobi", initializeBettingMoney()),
                        Player.of("ash", initializeBettingMoney()),
                        Player.of("ash", initializeBettingMoney())), "플레이어 중복 입력")
        );
    }

    private static BettingMoney initializeBettingMoney() {
        return BettingMoney.of(10);
    }
}
