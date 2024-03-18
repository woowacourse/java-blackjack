package blackjack.model.result;

import static blackjack.model.fixture.HandFixture.BLACKJACK_HAND;
import static blackjack.model.fixture.HandFixture.NOT_BLACKJACK_BUT_21_HAND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultRuleTest {

    @Test
    @DisplayName("딜러와 비교하여 결과를 계산한다.")
    void calculateResult() {
        ResultRule resultRule = new ResultRule(new Dealer(NOT_BLACKJACK_BUT_21_HAND.getHand()));
        Player player = Player.of("리브", BLACKJACK_HAND.getHand());
        assertThat(resultRule.calculateResult(player)).isEqualTo(ResultCommand.WIN);
    }

}
