package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_ACE;
import static blackjack.domain.CardFixture.SPADE_TEN;
import static blackjack.domain.CardFixture.SPADE_THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantBlackjackTest {

    private State blackjack;

    @BeforeEach
    void setUp() {
        blackjack = Ready.dealToParticipant(SPADE_ACE, SPADE_TEN);
    }

    @Test
    @DisplayName("참가자가 blackjack이면 ParticipantBlackjack이 된다.")
    void dealer_bust() {
        assertThat(blackjack).isInstanceOf(ParticipantBlackjack.class);
    }

    @Test
    @DisplayName("참가자가 blackjack으로 이기면 배팅 금액의 1.5배의 수익 금액을 반환한다.")
    void profit_win() {
        int money = 10000;
        Profit profit = blackjack.profit(Outcome.WIN, new BetMoney(money));

        assertThat(profit.get()).isEqualTo((int) (money * 1.5));
    }

    @Test
    @DisplayName("참가자가 blackjack으로 비기면 수익 금액을 0으로 반환한다.")
    void profit_draw() {
        int money = 10000;
        Profit profit = blackjack.profit(Outcome.DRAW, new BetMoney(money));

        assertThat(profit.get()).isZero();
    }

    @Test
    @DisplayName("blackjack일 때 draw를 하면 에러가 발생한다.")
    void draw() {
        assertThatThrownBy(() -> blackjack.draw(SPADE_THREE))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("blackjack일 때 stay를 하면 에러가 발생한다.")
    void stay() {
        assertThatThrownBy(blackjack::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}
