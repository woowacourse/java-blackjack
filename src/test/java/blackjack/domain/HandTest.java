package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("패 2장을 가진 핸드를 생성한다")
    @Test
    public void createSuccess() {
        assertThatCode(() -> new Hand())
                .doesNotThrowAnyException();
    }

    @DisplayName("패를 추가한다")
    @Test
    public void addCard() {
        Hand hand = new Hand();

        hand.put(new Card(CardSuit.SPADE, CardNumber.ACE));

        assertThat(hand.calculate()).isEqualTo(11);
    }

    @DisplayName("패가 2장이 아닌 경우 예외가 발생한다")
    @Test
    public void createFail() {
        Hand hand = new Hand();
        assertThatCode(() -> hand.initialize(
                List.of(new Card(CardSuit.HEART, CardNumber.ACE))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 핸드는 2장만 가질 수 있습니다.");
    }

    @DisplayName("패의 모든 수를 계산한다")
    @Test
    public void calculate() {
        Hand hand = new Hand();

        hand.initialize(List.of(new Card(CardSuit.HEART, CardNumber.ACE), new Card(CardSuit.CLOVER, CardNumber.THREE)));

        assertThat(hand.calculate()).isEqualTo(14);
    }
}
