package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StayTest {

    private Stay stay;

    @BeforeEach
    void setUp() {
        stay = new Stay(new Hand(Collections.singletonList(new Card(Pattern.DIAMOND, Number.NINE))));
    }

    @Test
    @DisplayName("Stay 상태의 배율 제대로 반환된다.")
    void stayEarningTest() {
        /*when*/
        double earningRate = stay.earningRate();

        /*then*/
        assertThat(earningRate).isEqualTo(1.0d);
    }

    @Test
    @DisplayName("stay메서드 호출하면 예외발생한다.")
    void stayTest() {
        assertThatThrownBy(() -> {
            stay.stay();
        }).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("receiveCard 메서드 호출하면 예외 발생한다.")
    void receiveCardTest() {
        assertThatThrownBy(() -> {
            stay.receiveCard(new Card(Pattern.DIAMOND, Number.NINE));
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
