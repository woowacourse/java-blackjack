package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WalletTest {

    @Test
    @DisplayName("에서 플레이어의 자금을 저장한다.")
    void put() {
        // given
        Wallet wallet = new Wallet();
        Money money = Money.from("5000");

        // when
        wallet.put("seyang", money);

        // then
        assertThat(wallet.get())
                .isEqualTo(Map.of("seyang", 5000));
    }
}
