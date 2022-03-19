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
                Arguments.of(List.of(new Player("a", initializeBettingMoney()),
                        new Player("b", initializeBettingMoney()),
                        new Player("c", initializeBettingMoney()),
                        new Player("d", initializeBettingMoney()),
                        new Player("e", initializeBettingMoney()),
                        new Player("f", initializeBettingMoney()),
                        new Player("g", initializeBettingMoney()),
                        new Player("h", initializeBettingMoney()),
                        new Player("i", initializeBettingMoney())), "플레이어 9명 입력"),
                Arguments.of(List.of(new Player("pobi", initializeBettingMoney()),
                        new Player("ash", initializeBettingMoney()),
                        new Player("ash", initializeBettingMoney())), "플레이어 중복 입력")
        );
    }

    private static BettingMoney initializeBettingMoney() {
        return BettingMoney.of(10);
    }
}
