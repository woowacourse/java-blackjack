package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    @DisplayName("플레이어가 이겼을 때 수익금이 올바르게 반환되는지 확인한다.")
    @Test
    void win_profit() {
        Bank bank = new Bank();
        GameResult gameResult = new GameResult();
        Player player = new Player("pobi");

        bank.bet(player, Money.of("5000"));
        gameResult.putResult(player, Profit.WIN);
        Money money = bank.getProfit(gameResult, player);

        assertThat(money.getMoney()).isEqualTo(5000);
    }
}

