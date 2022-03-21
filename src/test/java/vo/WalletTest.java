package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.vo.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WalletTest {
    private static final int DEFAULT_ZERO_MONEY = 0;

    @Test
    @DisplayName("Wallet 객체 생성 테스트")
    void createWallet() {
        // given
        String expectedString = "pobi";
        int expectedMoney = 1000;
        Wallet wallet = Wallet.of(expectedString);
        Wallet wallet2 = Wallet.of(expectedString, expectedMoney);

        // when
        String name = wallet.getName();
        int money = wallet.getMoney();
        String name2 = wallet2.getName();
        int money2 = wallet2.getMoney();

        // then
        assertAll(
                () -> assertThat(name).isEqualTo(expectedString),
                () -> assertThat(money).isEqualTo(DEFAULT_ZERO_MONEY),
                () -> assertThat(name2).isEqualTo(expectedString),
                () -> assertThat(money2).isEqualTo(expectedMoney)
        );
    }
}
