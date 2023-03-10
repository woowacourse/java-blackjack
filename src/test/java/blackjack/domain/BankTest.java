package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankTest {

    private Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
    }

    @Test
    @DisplayName("플레이어의 배팅 금액을 가진다.")
    void betMoneyTest() {
        Player player = new Player("player1");
        Money money = new Money(1000);

        bank.betMoney(player, money);

        assertThat(bank.getBank().get(player)).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("플레이어의 배팅 금액을 반환한다.")
    void findMoney() {
        Player player = new Player("player1");
        Money money = new Money(1000);

        bank.betMoney(player, money);

        assertThat(bank.findMoney(player)).isEqualTo(new Money(1000));
    }
}
