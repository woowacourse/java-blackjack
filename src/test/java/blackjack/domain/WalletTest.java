package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import blackjack.utils.HandFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class WalletTest {

    @DisplayName("배팅 금액을 입력받는다")
    @ParameterizedTest
    @ValueSource(ints = {1000, 50000, 1000000})
    void test1(int money) {

        assertThatCode(() -> Wallet.bet(money)).doesNotThrowAnyException();
    }

    @DisplayName("베팅 금액은 1억을 넘을 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {1000000000, 100000001})
    void test5(int money) {

        assertThatThrownBy(() -> Wallet.bet(money)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MAXIMUM_BETTING_MONEY.getMessage());
    }

    @DisplayName("배팅 금액은 양수가 아니면 예외를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void test2(int money) {

        assertThatThrownBy(() -> Wallet.bet(money)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.BETTING_MONEY_IS_POSITIVE.getMessage());
    }

    public static Stream<Arguments> betArguments() {
        return Stream.of(Arguments.of(1000, GameResultType.WIN, 1000),
                Arguments.of(1000, GameResultType.LOSE, -1000),
                Arguments.of(1000, GameResultType.PUSH, 0));
    }

    @DisplayName("초기 금액과 베팅 후 금액에 대한 수익을 반환한다.")
    @ParameterizedTest
    @MethodSource("betArguments")
    void test4(int money, GameResultType gameResultType, int expect) {
        // given
        Hand normal = HandFixture.normal();
        Wallet wallet = Wallet.bet(money);

        // when
        wallet.calculate(gameResultType, normal);

        // then
        assertThat(wallet.getRevenue()).isEqualTo(expect);
    }

}
