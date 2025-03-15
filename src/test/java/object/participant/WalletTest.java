package object.participant;

import object.game.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WalletTest {
    @Test
    void 월렛_생성_테스트() {
        // given

        // when
        Wallet wallet = new Wallet();

        // then
        Assertions.assertThat(wallet).isInstanceOf(Wallet.class);
    }

    @ParameterizedTest
    @CsvSource({
            "1000, BLACKJACK_WIN, 1500",
            "1000, WIN, 1000",
            "1000, DRAW, 0",
            "1000, LOSE, -1000",
    })
    void 월렛_베팅률_적용_테스트(int betMoney, GameResult gameResult, int expectedProfit) {
        // given
        Wallet wallet = new Wallet();
        wallet.betMoney(betMoney);

        // when
        wallet.applyBetRateBy(gameResult);

        // then
        int actual = wallet.getProfit();
        Assertions.assertThat(actual).isEqualTo(expectedProfit);
    }

    @Test
    void 베팅_금액_추가_테스트() {
        // given
        Wallet wallet = new Wallet();

        // when
        wallet.betMoney(1000);

        // then
        int expected = 1000;
        int actual = wallet.getBetMoney();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
