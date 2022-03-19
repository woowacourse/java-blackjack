package blackjack.domain.state;

import static blackjack.fixtures.CardFixtures.SPADE_ACE;
import static blackjack.fixtures.CardFixtures.SPADE_EIGHT;
import static blackjack.fixtures.CardFixtures.SPADE_FOUR;
import static blackjack.fixtures.CardFixtures.SPADE_KING;
import static blackjack.fixtures.CardFixtures.SPADE_NINE;
import static blackjack.fixtures.CardFixtures.SPADE_SEVEN;
import static blackjack.fixtures.CardFixtures.SPADE_TEN;
import static blackjack.fixtures.CardFixtures.SPADE_THREE;
import static blackjack.fixtures.CardFixtures.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("첫 두장의 합이 21(블랙잭)인 경우 블랙잭을 반환한다.")
    void blackjack() {
        State state = Ready.start(SPADE_ACE, SPADE_KING);

        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("첫 두장의 합이 21(블랙잭)이 아닌 경우 Hit를 반환한다.")
    void notBlackjack() {
        State state = Ready.start(SPADE_ACE, SPADE_NINE);

        assertThat(state.blackjack()).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("첫 두장의 합이 21(블랙잭)인 경우 배팅금액의 1.5배를 받는다.")
    void blackjackMoney() {
        State state = Ready.start(SPADE_ACE, SPADE_KING);

        assertThat(state.profit(new BettingMoney(10000))).isEqualTo(15000);
    }

    @Test
    @DisplayName("첫 두장의 합이 21이 아닌 경우 Hit을 반환한다.")
    void hit() {
        State state = Ready.start(SPADE_ACE, SPADE_SEVEN);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 추가로 뽑다가 21이 넘지 않는 경우 Hit를 반환한다.")
    void moreHit() {
        State state = Ready.start(SPADE_TWO, SPADE_THREE);
        state = state.draw(SPADE_FOUR);

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 추가로 뽑다가 21이 넘는 경우 Bust를 반환한다.")
    void bust() {
        State state = Ready.start(SPADE_TEN, SPADE_KING);
        state = state.draw(SPADE_TWO);

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드를 뽑다가 21이 넘는 경우 배팅금액을 잃는다.")
    void bustMoney() {
        State state = Ready.start(SPADE_THREE, SPADE_KING);
        state = state.draw(SPADE_NINE);

        assertThat(state.profit(new BettingMoney(10000))).isEqualTo(-10000);
    }

    @Test
    @DisplayName("카드를 그만 받는 경우 Stay를 반환한다.")
    void stay() {
        State state = Ready.start(SPADE_TEN, SPADE_KING);
        state = state.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("준비 상태에서 stay를 하는 경우 예외를 발생한다.")
    void isBlackjackByFinishedSate() {
        State ready = new Ready(new HoldCards());

        assertThatIllegalStateException()
            .isThrownBy(ready::stay)
            .withMessage("Ready 상태에서 Stay 할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이를 진행중에는 끝나지 않음을 반환한다.")
    void isNotFinished() {
        State ready = new Ready(new HoldCards());

        assertThat(ready.isFinished()).isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이 넘는 경우 Finished 상태가 된다.")
    void isFinished() {
        State state = Ready.start(SPADE_EIGHT, SPADE_SEVEN);
        state = state.draw(SPADE_NINE);

        assertThat(state.isFinished()).isTrue();
    }

    @Test
    @DisplayName("플레이를 진행중에 수익을 확인하는 경우 예외를 발생한다.")
    void profitByRunning() {
        State ready = new Ready(new HoldCards());

        assertThatIllegalStateException()
            .isThrownBy(() -> ready.profit(new BettingMoney(1000)))
            .withMessage("Running 상태에서 수입을 확인할 수 없습니다.");
    }
}
