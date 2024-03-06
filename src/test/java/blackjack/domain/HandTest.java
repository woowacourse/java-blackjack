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
        assertThatCode(() -> new Hand(List.of(CardFixture.diamond3(), CardFixture.heartJack())))
                .doesNotThrowAnyException();
    }

    @DisplayName("패가 2장이 아닌 경우 예외가 발생한다")
    @Test
    public void createFail() {
        assertThatCode(() -> new Hand(List.of(CardFixture.heartJack())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("초기 핸드는 2장만 가질 수 있습니다.");
    }

    @DisplayName("패의 모든 수를 계산한다")
    @Test
    public void calculate() {
        Hand hand = new Hand(List.of(CardFixture.diamond3(), CardFixture.heartJack()));

        assertThat(hand.calculate()).isEqualTo(13);
    }
}
