package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackRuleTest {

    @DisplayName("21이면 블랙잭")
    @ParameterizedTest
    @CsvSource(value = {"20, false", "21, true", "22, false"})
    void isBlackJack(int score, boolean expected) {
        assertThat(BlackJackRule.isBlackJack(score) == expected).isTrue();
    }

    @DisplayName("21초과하면 Bust")
    @ParameterizedTest
    @CsvSource(value = {"20, false", "21, false", "22, true"})
    void isBust(int score, boolean expected) {
        assertThat(BlackJackRule.isBust(score) == expected).isTrue();
    }

    @DisplayName("딜러가 드로우할 수 있는지 여부 확인 - 기준 16")
    @ParameterizedTest
    @CsvSource(value = {"15, true", "16, true", "17, false"})
    void isDealerDraw(int delearScore, boolean expected) {
        assertThat(BlackJackRule.isDealerDraw(delearScore) == expected).isTrue();
    }
}
