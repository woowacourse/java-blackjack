package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.player.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BettingMachineTest {

    @ParameterizedTest(name = "[{index}] {0}원 배팅 -> {1}원")
    @CsvSource({"10000, -10_000", "40000, -40_000", "100000, -100_000"})
    @DisplayName("참가자가 원하는 금액을 배팅한다.")
    void betMoney(String input, int expected) {
        Participant participant = new Participant("김제니");

        BettingMachine bettingMachine = new BettingMachine();
        bettingMachine.betMoney(participant, Money.from(input));

        assertThat(bettingMachine.getMoneys()).contains(entry(participant, Money.from(expected)));
    }
}
