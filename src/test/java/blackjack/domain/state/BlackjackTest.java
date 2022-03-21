package blackjack.domain.state;

import static blackjack.fixtures.BlackjackFixtures.SPADE_ACE;
import static blackjack.fixtures.BlackjackFixtures.SPADE_EIGHT;
import static blackjack.fixtures.BlackjackFixtures.SPADE_KING;
import static org.assertj.core.api.Assertions.assertThat;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {

    @Test
    @DisplayName("블랙잭은 배팅금액의 1.5배를 얻는다.")
    void blackjackRate() {
        Blackjack blackjack = new Blackjack(new HoldCards());

        assertThat(blackjack.earningRate(new Dealer(HoldCards.initTwoCards(SPADE_ACE, SPADE_EIGHT)))).isEqualTo(1.5);
    }

    @Test
    @DisplayName("딜러도 블랙잭일 경우 배팅금액의 1배를 얻는다.")
    void drawBlackjack() {
        Blackjack blackjack = new Blackjack(new HoldCards());

        assertThat(blackjack.earningRate(new Dealer(HoldCards.initTwoCards(SPADE_ACE, SPADE_KING)))).isEqualTo(1);
    }
}
