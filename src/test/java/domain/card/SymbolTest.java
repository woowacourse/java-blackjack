package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {
    @Test
    @DisplayName("Symbol 객체가 잘 생성되는 지 확인")
    void valueOf() {
        assertThat(Symbol.valueOf("ACE")).isEqualTo(Symbol.ACE);
    }

    @Test
    @DisplayName("Symbol word 생성 확인")
    void getWord() {
        assertThat(Symbol.ACE.getPattern()).isEqualTo("A");
    }

    @Test
    @DisplayName("Symbol value를 확인하는 테스트 코드")
    void getValue() {
        assertThat(Symbol.KING.getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("#calculate() : should return value")
    void calculateWithDefaultCalculation() {
        int sum = 11;
        for (Symbol symbol : Symbol.values()) {
            int score = symbol.calculate(sum);
            assertThat(score).isEqualTo(symbol.getValue());
        }
    }

    @Test
    @DisplayName("#calculate() : should return value + ADDITIONAL_ACE_VALUE with ACE")
    void calculateAceWithSmallSum() {
        int smallSum = 10;
        int aceAdditionalValue = 10;
        int score = Symbol.ACE.calculate(smallSum);
        assertThat(score).isEqualTo(Symbol.ACE.getValue() + aceAdditionalValue);
    }
}
