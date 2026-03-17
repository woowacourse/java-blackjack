package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @Test
    @DisplayName("양수면 정상 생성")
    void of_good() {
        int posNum = 10000;

        assertDoesNotThrow(
                () -> BetAmount.of(posNum)
        );
    }

    @ParameterizedTest
    @DisplayName("0원 이하를 베팅할 수는 없다.")
    @ValueSource(ints = {0, -1, -100})
    void zero_mean_not_bet_yet(int betAmount) {
        //then
        assertThatThrownBy(() -> BetAmount.of(betAmount));
    }
}