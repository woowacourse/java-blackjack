package model.bet;

import bet.Money;
import bet.ProfitStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Player;
import participant.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProfitStatusTest {
    @DisplayName("플레이어 배팅 상태가 초기화 되는지")
    @ParameterizedTest
    @MethodSource("makeProfitStatusTestData")
    void plusProfitMoneyTest(Player expectedPlayer, Money expectedMoney) {
        //given
        Map<Player, Money> profits = new HashMap<>();
        Players players = Players.from(List.of("hippo", "pobi", "james"));
        for (Player player : players.getPlayers()) {
            profits.put(player, new Money(1000));
        }

        //when
        ProfitStatus profitStatus = new ProfitStatus(profits);
        Map<Player, Money> values = profitStatus.getValues();

        //then
        Assertions.assertThat(values).containsEntry(expectedPlayer, expectedMoney);
    }

    private static Stream<Arguments> makeProfitStatusTestData() {
        return Stream.of(
                Arguments.arguments(
                        new Player("hippo"),
                        new Money(1000)
                ),
                Arguments.arguments(
                        new Player("pobi"),
                        new Money(1000)
                ),
                Arguments.arguments(
                        new Player("james"),
                        new Money(1000)
                )
        );
    }
}
