package blackjack.domain.rule;

import static blackjack.domain.rule.BasicRule.*;
import static blackjack.domain.rule.MoneyRule.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyRuleTest {

    static Stream<Arguments> basicRules() {
        return Stream.of(
            Arguments.of(WIN_BLACK_JACK, WIN_BLACK_JACK_MONEY),
            Arguments.of(WIN, WIN_MONEY),
            Arguments.of(DRAW, DRAW_MONEY),
            Arguments.of(LOSE, LOSE_MONEY)
        );
    }

    @DisplayName("BasicRule을 넣어서 같은 MoneyRule을 가져오는지 테스트")
    @ParameterizedTest
    @MethodSource("basicRules")
    void getMoneyUleTest(BasicRule basicRule, MoneyRule moneyRule) {
        assertThat(MoneyRule.getMoneyRule(basicRule)).isEqualTo(moneyRule);
    }
}
