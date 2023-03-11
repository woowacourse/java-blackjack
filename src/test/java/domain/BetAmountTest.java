package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000})
    void validBetAmount(int betAmount) {
        Assertions.assertThatThrownBy(() -> new BetAmount(betAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
