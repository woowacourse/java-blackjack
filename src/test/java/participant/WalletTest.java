package participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @Test
    @DisplayName("수익을 반환한다.")
    void test() {
        // given
        int bettingMoney = 10000;
        Wallet wallet = Wallet.of(bettingMoney);

        // when
        double rate = 1.5;
        wallet.updateMoney(rate);
        double result = wallet.calculateProfit();

        // then
        assertThat(result)
                .isEqualTo(bettingMoney * rate);
    }
}
