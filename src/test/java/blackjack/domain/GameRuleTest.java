package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRuleTest {
    @DisplayName("합계가 16이하인지 확인한다.")
    @Test
    void isUnderStandard() {
        GameRule gameRule = new GameRule();
        assertThat(gameRule.isUnderStandard(14)).isTrue();
    }

    @DisplayName("합계가 16초과인지 확인한다.")
    @Test
    void isAboveStandard(){
        GameRule gameRule = new GameRule();
        assertThat(gameRule.isUnderStandard(17)).isFalse();
    }
}
