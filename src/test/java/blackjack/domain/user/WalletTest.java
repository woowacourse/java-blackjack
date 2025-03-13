package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.game.GameResult;
import blackjack.user.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WalletTest {

    @Nested
    class CreateWalletTest {

        @ParameterizedTest
        @ValueSource(ints = {10000, 10001, 9999999, 10000000})
        @DisplayName("원금은 1만원에서 1000만원까지 입력할 수 있다.")
        void createWallet(int principal) {
            Wallet wallet = Wallet.initialBetting(principal);

            assertThat(wallet).isInstanceOf(Wallet.class);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 999, 10000001})
        @DisplayName("원금이 1만원미만 1000만원초과라면 입력할 수 없다.")
        void notCreateWallet(int principal) {
            assertThatThrownBy(() -> Wallet.initialBetting(principal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("원금은 1만원에서 1000만원까지 입력할 수 있습니다.");
        }
    }

    @Nested
    class ProfitTest {

        @Test
        @DisplayName("블랙잭이면 원금의 1.5배의 이익을 얻는다")
        void calculateBlackjackProfit() {
            Wallet initialWallet = Wallet.initialBetting(10000);
            GameResult gameResult = GameResult.WIN;
            boolean isBlackjack = true;

            Wallet resultWallet = initialWallet.calculateProfit(gameResult, isBlackjack);
            assertThat(resultWallet.getProfit()).isEqualTo(15000);
        }

        @Test
        @DisplayName("블랙잭이 아닌 승리는 원금 만큼의 이익을 얻는다")
        void calculateJustWinProfit() {
            Wallet initialWallet = Wallet.initialBetting(10000);
            GameResult gameResult = GameResult.WIN;
            boolean isBlackjack = false;

            Wallet resultWallet = initialWallet.calculateProfit(gameResult, isBlackjack);
            assertThat(resultWallet.getProfit()).isEqualTo(10000);
        }

        @Test
        @DisplayName("무승부는 이익이 없다")
        void calculateDrawProfit() {
            Wallet initialWallet = Wallet.initialBetting(10000);
            GameResult gameResult = GameResult.DRAW;
            boolean isBlackjack = false;

            Wallet resultWallet = initialWallet.calculateProfit(gameResult, isBlackjack);
            assertThat(resultWallet.getProfit()).isEqualTo(0);
        }

        @Test
        @DisplayName("패배는 원금 만큼을 잃는다")
        void calculateLoseProfit() {
            Wallet initialWallet = Wallet.initialBetting(10000);
            GameResult gameResult = GameResult.LOSE;
            boolean isBlackjack = false;

            Wallet resultWallet = initialWallet.calculateProfit(gameResult, isBlackjack);
            assertThat(resultWallet.getProfit()).isEqualTo(-10000);
        }
    }
}
