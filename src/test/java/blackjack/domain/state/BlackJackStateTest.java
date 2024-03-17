package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.BlackjackCardFactory;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Hand;
import blackjack.domain.participant.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackStateTest {

    @DisplayName("블랙잭 상태에서 드로우를 하면 에러가 발생한다")
    @Test
    public void drawFail() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThatCode(() -> blackJackState.draw(Deck.of(new BlackjackCardFactory(), cards -> cards)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @DisplayName("블랙잭 상태에서 스탠드를 하면 에러가 발생한다")
    @Test
    public void standFail() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThatCode(blackJackState::stand)
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

    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아니면 1.5를 반환한다")
    @Test
    public void getProfitRate() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThat(blackJackState.getProfitRate(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.TWO)))).isEqualTo(1.5);
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 1을 반환한다")
    @Test
    public void getProfitRate2() {
        BlackJackState blackJackState = new BlackJackState(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK)));

        assertThat(blackJackState.getProfitRate(Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.KING)))).isEqualTo(1);
    }
}
