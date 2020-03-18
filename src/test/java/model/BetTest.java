package model;

import exception.BetFormatException;
import exception.BetRangeException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BetTest {
    @ParameterizedTest
    @ValueSource(strings = {"99a", "안녕하세요", "-woowacourse"})
    void formatTest(String input){
        assertThatThrownBy(()->new Bet(input))
                .isInstanceOf(BetFormatException.class)
                .hasMessageMatching("베팅금액은 실수만 입력 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"99", "0", "-1000"})
    void rangeTest(String input){
        assertThatThrownBy(()->new Bet(input))
                .isInstanceOf(BetRangeException.class)
                .hasMessageMatching("베팅금액은 100원 이상부터 입력 가능합니다.");
    }
}
