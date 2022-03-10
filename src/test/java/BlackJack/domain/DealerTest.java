package BlackJack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Dealer dealer = new Dealer(new Cards(Arrays.asList(new Card(Shape.HEART, Number.JACK))));
        assertThat(dealer.checkScore()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Dealer dealer = new Dealer(new Cards(Arrays.asList(new Card(Shape.HEART, Number.JACK), new Card(Shape.HEART, Number.TEN))));
        assertThat(dealer.checkScore()).isEqualTo(false);
    }

}