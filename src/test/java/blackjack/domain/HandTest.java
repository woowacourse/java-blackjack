package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("패를 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new Hand(List.of(CardFixture.diamond3(), CardFixture.heartJack())))
                .doesNotThrowAnyException();
    }

    @DisplayName("패의 모든 수를 계산한다")
    @Test
    public void calculate() {
        Hand hand = new Hand(List.of(CardFixture.diamond3(), CardFixture.heartJack()));

        assertThat(hand.calculate()).isEqualTo(13);
    }
}
