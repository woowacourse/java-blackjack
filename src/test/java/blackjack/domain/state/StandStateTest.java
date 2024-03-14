package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.CardFactory;
import blackjack.domain.CardFixture;
import blackjack.domain.Deck;
import blackjack.domain.Denomination;
import blackjack.domain.Hand;
import blackjack.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StandStateTest {

    @DisplayName("스탠드 상태에서 드로우하면 에러가 발생한다")
    @Test
    public void drawFail() {
        StandState standState = new StandState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.NINE)));

        assertThatCode(() -> standState.draw(Deck.of(new CardFactory(), cards -> cards)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @DisplayName("스탠드 상태에서 스탠드하면 에러가 발생한다")
    @Test
    public void standFail() {
        StandState standState = new StandState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.NINE)));

        assertThatCode(standState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @DisplayName("스탠드 상태에서 끝났는지 확인하면 true를 반환한다")
    @Test
    public void isFinishedTrue() {
        StandState standState = new StandState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.NINE)));

        assertThat(standState.isFinished()).isTrue();
    }

    @DisplayName("스탠드 상태에서 핸드를 계산하면 점수를 반환한다")
    @Test
    public void calculateHand() {
        StandState standState = new StandState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.NINE)));

        assertThat(standState.calculateHand()).isEqualTo(Score.from(19));
    }
}

