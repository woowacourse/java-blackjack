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

public class BlackJackStateTest {

    @DisplayName("블랙잭 상태에서 드로우를 하면 에러가 발생한다")
    @Test
    public void drawFail() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThatCode(() -> blackJackState.draw(Deck.of(new CardFactory(), cards -> cards)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @DisplayName("블랙잭 상태에서 스탠드를 하면 에러가 발생한다")
    @Test
    public void standFail() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThatCode(() -> blackJackState.stand())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @DisplayName("블랙잭 상태에서 끝났는지 확인하면 true를 반환한다")
    @Test
    public void isFinishedTrue() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThat(blackJackState.isFinished()).isTrue();
    }

    @DisplayName("블랙잭 상태에서 카드를 계산하면 점수가 반환된다")
    @Test
    public void calculateHand() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThat(blackJackState.calculateHand()).isEqualTo(Score.from(21));
    }
}
