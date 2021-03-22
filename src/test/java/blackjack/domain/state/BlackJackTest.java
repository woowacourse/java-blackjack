package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Number;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.hand.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackTest {

    private Blackjack blackjack;

    @BeforeEach
    void setUp() {
        blackjack = new Blackjack(new Hand(Collections.singletonList(new Card(Pattern.DIAMOND, Number.NINE))));
    }

    @Test
    @DisplayName("Blackjack 상태의 배율 제대로 반환된다.")
    void stayEarningTest() {
        //given
        double earningRate = blackjack.earningRate();

        //then
        assertThat(earningRate).isEqualTo(1.5d);
    }

    @Test
    @DisplayName("Blackjack 상태일 때 isBlackJack을 호출하면 true를 반환")
    void isBlackjackTest() {
        //given
        boolean isBlackjack = blackjack.isBlackJack();

        //then
        assertThat(isBlackjack).isTrue();
    }
}
