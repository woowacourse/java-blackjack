package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("핸드를 생성한다")
    @Test
    public void createSuccess() {
        assertThatCode(Hand::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("패를 추가한다")
    @Test
    public void addCard() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());

        assertThat(hand.getCards().size()).isEqualTo(1);
    }

    @DisplayName("패의 모든 수를 계산한다")
    @Test
    public void calculate() {
        Hand hand = new Hand();

        hand.put(CardFixture.heartJack());
        hand.put(CardFixture.diamond3());

        assertThat(hand.calculate()).isEqualTo(13);
    }
}
