package blackjack.state.started.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.card.Card;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.card.Hand;
import fixture.HandFixture;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {
    @DisplayName("Running 상태에서 수익을 구하고자 하면 예외가 발생한다.")
    @Test
    void cannotGetProfit() {
        // given
        Hand hand = HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.KING),
                Card.of(CardSymbol.SPADE, CardRank.JACK));
        Hit hitState = new Hit(hand);

        // when // then
        assertThatCode(() -> hitState.getProfit(BigDecimal.valueOf(1000)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("Running 상태의 isFinished는 false이다.")
    @Test
    void isFinished() {
        // given
        Hand hand = HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.KING),
                Card.of(CardSymbol.SPADE, CardRank.JACK));
        Hit hitState = new Hit(hand);

        // when
        boolean actual = hitState.isFinished();

        // then
        assertThat(actual).isFalse();
    }


}
