package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BettingTableTest {

    @ParameterizedTest
    @MethodSource("createNameAndBetting")
    @DisplayName("참가자 이름과 배팅 금액을 가진다.")
    void createBettingTable(String playerName, Betting betting) {
        Map<String, Betting> bettingTable = new HashMap<>();
        bettingTable.put(playerName, betting);

        assertDoesNotThrow(() -> new BettingTable(bettingTable));
    }

    @ParameterizedTest
    @MethodSource("createNameAndBetting")
    @DisplayName("참가자 이름에 해당하는 배팅 금액을 반환한다.")
    void getBettingByName(String playerName, Betting betting) {
        Map<String, Betting> bettingInfo = new HashMap<>();
        bettingInfo.put(playerName, betting);

        BettingTable bettingTable = new BettingTable(bettingInfo);

        assertThat(bettingTable.getBetting(playerName)).isEqualTo(betting.getValue());
    }

    static Stream<Arguments> createNameAndBetting() {
        return Stream.of(
                Arguments.of("gray", new Betting(10_000),
                Arguments.of("kong", new Betting(50_000)),
                Arguments.of("luca", new Betting(100_000)),
                Arguments.of("bada", new Betting(1_000_000)))
        );
    }
}
