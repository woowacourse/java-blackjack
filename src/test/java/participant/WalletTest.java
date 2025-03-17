package participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {

    @Test
    @DisplayName("얻은 돈을 업데이트한다.")
    void test1() {
        // given
        int bettingMoney = 10000;
        Wallet wallet = Wallet.of(bettingMoney);

        // when
        int addedMoney = 10000;
        wallet.addMoney(addedMoney);

        // then
        assertThat(wallet.getEarnedMoney())
                .isEqualTo(bettingMoney + addedMoney);
    }

    @Test
    @DisplayName("잃은 돈을 업데이트한다.")
    void test2() {
        // given
        int bettingMoney = 10000;
        Wallet wallet = Wallet.of(bettingMoney);

        // when
        int subtractedMoney = 10000;
        wallet.subtractMoney(subtractedMoney);

        // then
        assertThat(wallet.getEarnedMoney())
                .isEqualTo(bettingMoney - subtractedMoney);
    }

    @Test
    @DisplayName("얻은 수익을 반환한다.")
    void test3() {
        // given
        int bettingMoney = 10000;
        Wallet wallet = Wallet.of(bettingMoney);

        // when
        int addedMoney = 10000;
        wallet.addMoney(addedMoney);

        // then
        assertThat(wallet.getProfit().getAmount())
                .isEqualTo(addedMoney);
    }

    @Test
    @DisplayName("잃은 수익을 반환한다.")
    void test4() {
        // given
        int bettingMoney = 10000;
        Wallet wallet = Wallet.of(bettingMoney);

        // when
        int subtractedMoney = 10000;
        wallet.subtractMoney(subtractedMoney);

        // then
        assertThat(wallet.getProfit().getAmount())
                .isEqualTo(-subtractedMoney);
    }
}
