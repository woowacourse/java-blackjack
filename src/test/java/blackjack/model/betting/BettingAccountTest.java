package blackjack.model.betting;

import static blackjack.model.PlayerFixture.BLACKJACK_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingAccountTest {

    @Test
    @DisplayName("추가로 돈을 지급받는다.")
    void receiveMoney() {
        BettingAccount bettingAccount = new BettingAccount(BLACKJACK_PLAYER.getPlayer(), new Money(1_000));

        bettingAccount.receive(2_000);
        assertThat(bettingAccount.getMoney()).isEqualTo(new Money(3_000));
    }
}
