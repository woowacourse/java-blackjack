package object.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WalletTest {
    @Test
    void 월렛_생성_테스트() {
        // given
        int betAmount = 1000;

        // when
        Wallet wallet = new Wallet(betAmount);

        // then
        Assertions.assertThat(wallet).isInstanceOf(Wallet.class);
    }
}
