package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BustTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(Collections.singletonList(new Card(Pattern.DIAMOND, Number.NINE)));
    }

    @Test
    @DisplayName("Bust 상태의 배율 제대로 반환된다.")
    void stayEarningTest() {
        /*give*/
        Bust bust = new Bust(hand);

        /*when*/
        double earningRate = bust.earningRate();

        /*then*/
        assertThat(earningRate).isEqualTo(-1.0d);
    }

    @Test
    @DisplayName("버스트 상태에서 isBust를 호출하면 true를 반환한다.")
    void isBustTest() {
        //give
        State state = new Bust(hand);

        //when
        boolean isBust = state.isBust();

        assertThat(isBust).isTrue();
    }
}
