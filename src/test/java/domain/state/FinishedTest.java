package domain.state;

import domain.card.Card;
import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinishedTest {

    @DisplayName("Bust 상태는 딜러의 상태와 상관없이 항상 수익률 -1.0을 반환하고 종료 상태이다")
    @Test
    void bustEarningRate_Always_IsMinusOne() {
        State playerState = new Bust(new Hand());
        State dealerState = new Stay(new Hand().appendCard(Card.from("10", "하트")));

        assertThat(playerState.isFinished()).isTrue();
        assertThat(playerState.earningRate(dealerState)).isEqualTo(-1.0);
    }

    @DisplayName("Blackjack 상태는 딜러가 Blackjack이 아니면 수익률 1.5을 반환하고 종료 상태이다")
    @Test
    void blackjackEarningRate_DealerIsNotBlackjack_IsOnePointFive() {
        State playerState = new Blackjack(new Hand());
        State dealerState = new Stay(new Hand());

        assertThat(playerState.isFinished()).isTrue();
        assertThat(playerState.earningRate(dealerState)).isEqualTo(1.5);
    }

    @DisplayName("Blackjack 상태는 딜러도 Blackjack이면 수익률 0.0(무승부)을 반환한다")
    @Test
    void blackjackEarningRate_BothBlackjack_ReturnsZero() {
        State playerState = new Blackjack(new Hand());
        State dealerState = new Blackjack(new Hand());

        assertThat(playerState.earningRate(dealerState)).isEqualTo(0.0);
    }
}
