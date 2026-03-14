package domain.state;

import domain.card.Card;
import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StayTest {

    @DisplayName("Stay 상태는 종료된 상태이다")
    @Test
    void isFinished_Always_ReturnTrue() {
        State state = new Stay(new Hand());
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("Stay 상태는 딜러가 Bust되면 점수와 상관없이 수익률 1.0을 반환한다")
    @Test
    void earningRate_DealerBust_ReturnsOnePointZero() {
        Hand playerHand = new Hand().appendCard(Card.from("10", "하트"))
                .appendCard(Card.from("5", "하트"));
        State playerState = new Stay(playerHand);

        State dealerState = new Bust(new Hand());

        assertThat(playerState.earningRate(dealerState)).isEqualTo(1.0);
    }

    @DisplayName("Stay 상태는 딜러가 Stay일 때 점수 비교를 통해 수익률을 반환한다")
    @ParameterizedTest
    @CsvSource({
            "10, 10, 10, 9, 1.0",
            "10, 8, 10, 9, -1.0",
            "10, 9, 10, 9, 0.0"
    })
    void earningRate_CompareScoreWithDealer_ReturnsExpectedRate(
            String p1, String p2, String d1, String d2, double expectedRate) {

        Hand playerHand = new Hand().appendCard(Card.from(p1, "하트")).appendCard(Card.from(p2, "다이아몬드"));
        State playerState = new Stay(playerHand);

        Hand dealerHand = new Hand().appendCard(Card.from(d1, "클로버")).appendCard(Card.from(d2, "스페이드"));
        State dealerState = new Stay(dealerHand);

        assertThat(playerState.earningRate(dealerState)).isEqualTo(expectedRate);
    }

    @DisplayName("Stay 상태에서 카드를 더 뽑으려 하면 예외가 발생한다")
    @Test
    void draw_WhenCalled_ThrowsException() {
        State state = new Stay(new Hand());
        assertThatThrownBy(() -> state.draw(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
