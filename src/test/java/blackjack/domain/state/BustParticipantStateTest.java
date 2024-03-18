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

public class BustParticipantStateTest {

    @DisplayName("버스트 상태에서 드로우를 하면 에러가 발생한다")
    @Test
    public void drawFail() {
        BustParticipantState bustState = new BustParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThatCode(() -> bustState.draw(Deck.of(new BlackjackCardsFactory(), cards -> cards)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @DisplayName("버스트 상태에서 스탠드를 하면 에러가 발생한다")
    @Test
    public void standFail() {
        BustParticipantState bustState = new BustParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThatCode(bustState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @DisplayName("버스트 상태에서 끝났는지 확인하면 true가 반환된다")
    @Test
    public void isFinishedTrue() {
        BustParticipantState bustState = new BustParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThat(bustState.isFinished()).isTrue();
    }

    @DisplayName("버스트 상태에서 핸드를 계산하면 점수가 반환된다")
    @Test
    public void calculateHand() {
        BustParticipantState bustState = new BustParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThat(bustState.calculateHand()).isEqualTo(Score.from(30));
    }

    @DisplayName("버스트 상태면 -1을 반환한다")
    @Test
    public void getProfitRate() {
        BustParticipantState bustState = new BustParticipantState(
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThat(bustState.getProfitRate(Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING)))).isEqualTo(-1);
    }
}
