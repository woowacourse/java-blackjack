package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitParticipantStateTest {

    @DisplayName("히트 상태에서 드로우를 했을 때 카드가 21을 넘지 않으면 히트상태가 된다")
    @Test
    public void hitToHit() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        ParticipantState newParticipantState = hitState.draw(
                Deck.of(new BlackjackCardsFactory(), cards -> cards)); // 정렬 안한 마지막 KING 카드를 드로우

        assertThat(newParticipantState).isInstanceOf(HitParticipantState.class);
    }

    @DisplayName("히트 상태에서 드로우를 했을 때 카드가 21을 초과하면 버스트 상태가 된다")
    @Test
    public void hitToBust() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        ParticipantState newParticipantState = hitState.draw(
                Deck.of(new BlackjackCardsFactory(), cards -> cards)); // 정렬 안한 마지막 KING 카드를 드로우

        assertThat(newParticipantState).isInstanceOf(BustParticipantState.class);
    }

    @DisplayName("히트 상태에서 스탠드를 하면 스탠드 상태가 된다")
    @Test
    public void hitToStand() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.stand()).isInstanceOf(StandParticipantState.class);
    }

    @DisplayName("히트 상태에서 끝났는지 확인하면 false를 반환한다")
    @Test
    public void isFinishedFalse() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.isFinished()).isFalse();
    }

    @DisplayName("히트 상태에서 핸드를 계산하면 점수를 반환한다")
    @Test
    public void calculateHand() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(hitState.calculateHand()).isEqualTo(Score.from(5));
    }

    @DisplayName("히트 상태에서 수익률을 구하려 하면 에러가 발생한다")
    @Test
    public void getProfitRate() {
        HitParticipantState hitState = new HitParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.THREE),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThatCode(() -> hitState.getProfitRate(Hand.of()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("현재 상태에서는 할 수 없습니다.");
    }
}
