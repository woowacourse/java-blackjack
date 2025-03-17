package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.WinningStatus;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BlackjackBettingMoneyTest {
    
    @ParameterizedTest
    @ValueSource(ints = {9_999, 1_000_001})
    void 베팅_금액이_범위를_넘어가는_경우_예외를_발생시킨다(int money) {
        // expected
        assertThatThrownBy(() -> new BlackjackBettingMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭 배팅은 10,000원부터 1,000,000원까지 가능합니다.");
    }
    
    @ParameterizedTest
    @MethodSource("provideBettingMoneyAndWinningStatusAndProfit")
    void 결과에_따라_배팅_금액이_달라진다(int money, WinningStatus winningStatus, Money  profit) {
        // given
        BlackjackBettingMoney bettingMoney = new BlackjackBettingMoney(money);
        
        // expected
        assertThat(bettingMoney.getProfit(winningStatus)).isEqualTo(profit);
    }
    
    private static Stream<Arguments> provideBettingMoneyAndWinningStatusAndProfit() {
        return Stream.of(
                Arguments.of(10000, WinningStatus.BLACKJACK_WIN, new Money(15000)),
                Arguments.of(10000, WinningStatus.WIN, new Money(10000)),
                Arguments.of(10000, WinningStatus.DRAW, new Money(0)),
                Arguments.of(10000, WinningStatus.LOSE, new Money(-10000))
        );
    }
}
