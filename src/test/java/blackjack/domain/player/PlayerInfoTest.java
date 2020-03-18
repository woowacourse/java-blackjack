package blackjack.domain.player;

import blackjack.domain.generic.BettingMoney;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerInfoTest {

    private static Stream<Arguments> playerInfoProvider() {
        return Stream.of(
                Arguments.of(null, BettingMoney.of(0), "이름이 비었습니다."),
                Arguments.of("bebop", null, "베팅 금액이 비었습니다.")
        );
    }

    @ParameterizedTest
    @MethodSource("playerInfoProvider")
    void nullThenThrowException(String name, BettingMoney bettingMoney, String message) {
        assertThatThrownBy(() -> new PlayerInfo(name, bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}