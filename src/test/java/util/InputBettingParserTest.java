package util;

import domain.betting.Money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputBettingParserTest {
    @Test
    @DisplayName("정상 입력에 대해서는 정상적으로 파싱한다.")
    void testSuccessCase() {
        Money money = InputBettingParser.parseBettingMoney("1000");
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("음수 입력에 대해서는 오류를 반환한다.")
    void testNegativeNumberParse() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> InputBettingParser.parseBettingMoney("-1000"));
    }

    @Test
    @DisplayName("0 입력에 대해서는 오류를 반환한다.")
    void testZeroParse() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> InputBettingParser.parseBettingMoney("0"));
    }

    @Test
    @DisplayName("공백이 숫자 앞뒤로 붙은 경우 정상적으로 파싱한다.")
    void trimParse() {
        Money money = InputBettingParser.parseBettingMoney("     1000");
        assertThat(money).isEqualTo(new Money(1000));
    }

    @Test
    @DisplayName("숫자가 아닌 다른 문자가 섞였을 때는 오류를 반환한다.")
    void notNumberParse() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
                () -> InputBettingParser.parseBettingMoney("1000원"));
    }
}
