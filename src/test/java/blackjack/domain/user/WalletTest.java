package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.user.Wallet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

}
