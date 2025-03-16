package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("Money 객체 생성 시 초기 금액이 유효한 경우 생성 성공")
    void testValidSeedMoney() {
        Money money = new Money(1000);
        assertEquals(1000, money.getSeedMoney());
    }

    @Test
    @DisplayName("Money 객체 생성 시 초기 금액이 0보다 작으면 예외 발생")
    void testInvalidSeedMoney() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Money(-500),
                "0보다 큰 금액만 베팅 가능합니다"
        );
        assertEquals("0보다 큰 금액만 베팅 가능합니다", exception.getMessage());
    }

    @Test
    @DisplayName("processBetting 메서드로 배팅 금액 계산")
    void testProcessBetting() {
        Money money = new Money(1000);

        money.processBetting(1.5); // 1000 * 1.5 = 1500
        assertEquals(1500, money.getEarnMoney());

        money.processBetting(-1.0); // 1000 * -1.0 = -1000
        assertEquals(-1000, money.getEarnMoney());
    }

    @Test
    @DisplayName("earnMoney 메서드로 금액 누적")
    void testEarnMoney() {
        Money money = new Money(1000);
        money.earnMoney(500); // 0 + 500 = 500
        assertEquals(500, money.getEarnMoney());

        money.earnMoney(200); // 500 + 200 = 700
        assertEquals(700, money.getEarnMoney());

        money.earnMoney(-300); // 700 - 300 = 400
        assertEquals(400, money.getEarnMoney());
    }
}
