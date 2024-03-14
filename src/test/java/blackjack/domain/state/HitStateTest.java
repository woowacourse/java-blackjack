package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardFactory;
import blackjack.domain.CardFixture;
import blackjack.domain.Deck;
import blackjack.domain.Denomination;
import blackjack.domain.Hand;
import blackjack.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitStateTest {

    @DisplayName("히트 상태에서 드로우를 했을 때 카드가 21을 넘지 않으면 히트상태가 된다")
    @Test
    public void hitToHit() {
        HitState hitState = new HitState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                CardFixture.fromSuitCloverWith(Denomination.TWO)));

        State newState = hitState.draw(Deck.of(new CardFactory(), cards -> cards)); // 정렬 안한 마지막 KING 카드를 드로우

        assertThat(newState).isInstanceOf(HitState.class);
    }

    @DisplayName("히트 상태에서 드로우를 했을 때 카드가 21을 초과하면 버스트 상태가 된다")
    @Test
    public void hitToBust() {
        HitState hitState = new HitState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                CardFixture.fromSuitCloverWith(Denomination.TWO)));

        State newState = hitState.draw(Deck.of(new CardFactory(), cards -> cards)); // 정렬 안한 마지막 KING 카드를 드로우

        assertThat(newState).isInstanceOf(BustState.class);
    }

    @DisplayName("히트 상태에서 스탠드를 하면 스탠드 상태가 된다")
    @Test
    public void hitToStand() {
        HitState hitState = new HitState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.stand()).isInstanceOf(StandState.class);
    }

    @DisplayName("히트 상태에서 끝났는지 확인하면 false를 반환한다")
    @Test
    public void isFinishedFalse() {
        HitState hitState = new HitState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.isFinished()).isFalse();
    }

    @DisplayName("히트 상태에서 핸드를 계산하면 점수를 반환한다")
    @Test
    public void calculateHand() {
        HitState hitState = new HitState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.calculateHand()).isEqualTo(Score.from(5));
    }
}
