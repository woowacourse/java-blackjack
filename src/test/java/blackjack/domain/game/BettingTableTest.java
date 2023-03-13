package blackjack.domain.game;

import blackjack.domain.participant.Name;
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
    void createBettingTable(String playerName, BettingMoney bettingMoney) {
        Map<Name, BettingMoney> bettingTable = new HashMap<>();
        bettingTable.put(new Name(playerName), bettingMoney);

        assertDoesNotThrow(() -> new BettingTable(bettingTable));
    }

    @ParameterizedTest
    @MethodSource("createNameAndBetting")
    @DisplayName("참가자 이름에 해당하는 배팅 금액을 반환한다.")
    void getBettingByName(String playerName, BettingMoney bettingMoney) {
        Map<Name, BettingMoney> bettingInfo = new HashMap<>();
        Name name = new Name(playerName);
        bettingInfo.put(name, bettingMoney);

        BettingTable bettingTable = new BettingTable(bettingInfo);

        assertThat(bettingTable.getBetting(name).getValue()).isEqualTo(bettingMoney.getValue());
    }

    static Stream<Arguments> createNameAndBetting() {
        return Stream.of(
                Arguments.of("gray", new BettingMoney(10_000),
                Arguments.of("kong", new BettingMoney(50_000)),
                Arguments.of("luca", new BettingMoney(100_000)),
                Arguments.of("bada", new BettingMoney(1_000_000)))
        );
    }
}
