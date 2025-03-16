package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @DisplayName("블랙잭으로 이긴 경우 수익은 배팅금의 1.5배이다")
    @Test
    void test1() {
        // given
        Money money = Money.of(100000);
        // when
        Money expected = GameResult.BLACKJACK.applyReturnRate(money);
        // then
        assertThat(expected).isEqualTo(Money.of(150000));
    }

    @Test
    void 숫자로_이긴_경우_수익은_배팅금의_1배이다() {
        // given
        Money money = Money.of(100000);
        // when
        Money expected = GameResult.WIN.applyReturnRate(money);
        // then
        assertThat(expected).isEqualTo(Money.of(100000));
    }

    @Test
    void 비긴_경우_수익은_배팅금의_0배이다() {
        // given
        Money money = Money.of(100000);
        // when
        Money expected = GameResult.DRAW.applyReturnRate(money);
        // then
        assertThat(expected).isEqualTo(Money.of(0));
    }

    @DisplayName("비긴 경우 수깅은 배팅금의 -1배이다")
    @Test
    void test2() {
        // given
        Money money = Money.of(100000);
        // when
        Money expected = GameResult.LOSE.applyReturnRate(money);
        // then
        assertThat(expected).isEqualTo(Money.of(-100000));
    }

}