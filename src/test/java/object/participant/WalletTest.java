package object.participant;

import object.game.BattleResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WalletTest {
    @Test
    void 월렛_생성_테스트() {
        // given
        int betMoney = 1000;

        // when
        Wallet wallet = Wallet.generateEmptyWalletFrom(betMoney);

        // then
        Assertions.assertThat(wallet).isInstanceOf(Wallet.class);
    }

    @Test
    void 월렛_베팅률_적용_테스트() {
        // given
        int betMoney = 1000;
        Wallet wallet = Wallet.generateEmptyWalletFrom(betMoney);

        // when
        Wallet appliedWallet = wallet.applyBetRate(BattleResult.WIN);

        // then
        int expected = 2000;
        int actual = appliedWallet.getEarnedMoney();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
