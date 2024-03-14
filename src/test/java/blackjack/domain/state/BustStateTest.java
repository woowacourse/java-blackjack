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

public class BustStateTest {

    @DisplayName("버스트 상태에서 드로우를 하면 에러가 발생한다")
    @Test
    public void drawFail() {
        BustState bustState = new BustState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING), CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThatCode(() -> bustState.draw(Deck.of(new CardFactory(), cards -> cards)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @DisplayName("버스트 상태에서 스탠드를 하면 에러가 발생한다")
    @Test
    public void standFail() {
        BustState bustState = new BustState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING), CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThatCode(bustState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @DisplayName("버스트 상태에서 끝났는지 확인하면 true가 반환된다")
    @Test
    public void isFinishedTrue() {
        BustState bustState = new BustState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING), CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThat(bustState.isFinished()).isTrue();
    }

    @DisplayName("버스트 상태에서 핸드를 계산하면 점수가 반환된다")
    @Test
    public void calculateHand() {
        BustState bustState = new BustState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.KING), CardFixture.fromSuitCloverWith(Denomination.KING)));

        assertThat(bustState.calculateHand()).isEqualTo(Score.from(30));
    }
}
