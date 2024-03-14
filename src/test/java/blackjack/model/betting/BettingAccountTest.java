package blackjack.model.betting;

import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAccountTest {

    @Test
    @DisplayName("특정 금액만큼의 돈을 계좌에 넣는다.")
    void receiveMoney() {
        BettingAccount bettingAccount = new BettingAccount(BLACKJACK_PLAYER.getPlayer(), new Money(1_000));

        bettingAccount.receive(2_000);
        assertThat(bettingAccount.getMoney()).isEqualTo(new Money(3_000));
    }

    @Test
    @DisplayName("특정 금액만큼의 돈을 뺀다.")
    void subtractMoney() {
        BettingAccount bettingAccount = new BettingAccount(BLACKJACK_PLAYER.getPlayer(), new Money(1_000));

        bettingAccount.withdraw(500);
        assertThat(bettingAccount.getMoney()).isEqualTo(new Money(500));
    }
}
