package blackjack.state.started.running;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.card.Hand;
import blackjack.state.State;
import blackjack.state.started.finished.Bust;
import blackjack.state.started.finished.Stay;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {
    @DisplayName("Hit 상태는 hit 이후 기본적으로 Hit 상태를 반환한다")
    @Test
    void hitToHit() {
        // given
        Hand hand = HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                Card.of(CardSymbol.SPADE, CardRank.EIGHT));
        Hit hitState = new Hit(hand);

        // when
        State actual = hitState.hit(Card.of(CardSymbol.SPADE, CardRank.TWO));

        // then
        assertThat(actual).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit 상태는 hit 이후 버스트라면 Bust 상태를 반환한다")
    @Test
    void hitToBust() {
        // given
        Hand hand = HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.KING),
                Card.of(CardSymbol.SPADE, CardRank.JACK));
        Hit hitState = new Hit(hand);

        // when
        State actual = hitState.hit(Card.of(CardSymbol.SPADE, CardRank.TWO));

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit 상태는 stay 이후 Stay 상태를 반환한다")
    @Test
    void hitToStay() {
        // given
        Hand hand = HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.EIGHT),
                Card.of(CardSymbol.SPADE, CardRank.EIGHT));
        Hit hitState = new Hit(hand);

        // when
        State actual = hitState.stay();

        // then
        assertThat(actual).isInstanceOf(Stay.class);
    }

}
