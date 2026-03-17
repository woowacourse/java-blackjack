package domain.player.attribute;

import domain.player.Gambler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("베팅 금액이 0일 때 에러 발생 검증")
    void 베팅_금액_0_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Money("0")
        );
    }

    @Test
    @DisplayName("베팅 금액이 음수 때 에러 발생 검증")
    void 베팅_금액_음수_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Money("-1")
        );
    }

    @Test
    @DisplayName("베팅 금액이 100억 넘을 때 에러 검증")
    void 베팅_금액_100억_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Money("10_000_000_000")
        );
    }
}