package blackjack.domain.participants;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BettingMoneyTest {

    @DisplayName("베팅 금액이 0 이하이면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void should_ThrowException_When_AmountIsUnder0(final int amount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BettingMoney(amount), "베팅 금액은 0보다 큰 수여야 합니다."
        );
    }
}
