package blackjack.domain.game;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Result;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class MoneyTest {

    @Test
    void 베팅_금액이_100_보다_작은_경우_예외를_던진다() {
        assertThatThrownBy(() -> Money.initialBet(99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 100 이상, 10000 이하여야 합니다.");
    }

    @ParameterizedTest(name = "베팅 금액이 100 단위가 아닌 경우 예외를 던진다. 입력: {0}")
    @ValueSource(ints = {101, 150, 199})
    void 베팅_금액이_100_단위가_아닌_경우_예외를_던진다(final int value) {
        assertThatThrownBy(() -> Money.initialBet(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅은 100 단위로 할 수 있습니다.");
    }

    @ParameterizedTest(name = "Result를 입력받아 계산된 Money를 반환한다. 입력: {0}, 결과: {1}")
    @CsvSource({"BLACKJACK_WIN,15000", "WIN,10000", "PUSH,0", "LOSE,-10000"})
    void Money는_Result를_입력받아_계산된_Money를_반환한다(final Result result, final int prize) {
        final Money bet = Money.initialBet(10000);

        final Money money = bet.calculatePrize(result);

        assertThat(money.getValue()).isEqualTo(prize);

    }

    @Test
    void 뺀_값을_반환한다() {
        final Money money = Money.initialBet(1000);

        Money result = money.minus(Money.initialBet(1500));

        assertThat(result.getValue()).isEqualTo(-500);
    }

    @Test
    void 값이_0인_Money를_반환한다() {
        final Money money = Money.ZERO;

        assertThat(money.getValue()).isEqualTo(0);
    }
}
