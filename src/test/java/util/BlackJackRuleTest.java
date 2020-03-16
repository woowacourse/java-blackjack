package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackRuleTest {

    @DisplayName("21이면 블랙잭")
    @Test
    void isBlackJack() {
        assertThat(BlackJackRule.isBlackJack(21)).isTrue();
        assertThat(BlackJackRule.isBlackJack(20)).isFalse();
        assertThat(BlackJackRule.isBlackJack(22)).isFalse();
    }

    @DisplayName("21초과하면 Bust")
    @Test
    void isBust() {
        assertThat(BlackJackRule.isBust(21)).isFalse();
        assertThat(BlackJackRule.isBust(22)).isTrue();
    }

    @DisplayName("16초과 여부 확인")
    @Test
    void isOverSixteen() {
        assertThat(BlackJackRule.canDealerHit(16)).isFalse();
        assertThat(BlackJackRule.canDealerHit(17)).isTrue();
    }
}
