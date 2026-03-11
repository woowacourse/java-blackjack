package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러_카드의_합이_17일때_카드를_추가로_뽑을_수_있는지_확인하는_기능_테스트() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(CardValue.SEVEN, CardShape.CLOVER));
        dealer.draw(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        boolean canDraw = dealer.canDraw();

        // then
        assertThat(canDraw).isFalse();
    }

    @Test
    void 딜러_카드의_합이_16일때_카드를_추가로_뽑을_수_있는지_확인하는_기능_테스트() {
        // given
        Dealer dealer = new Dealer();
        dealer.draw(new Card(CardValue.SIX, CardShape.CLOVER));
        dealer.draw(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when
        boolean canDraw = dealer.canDraw();

        // then
        assertThat(canDraw).isTrue();
    }

}
