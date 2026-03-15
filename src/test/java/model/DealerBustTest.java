package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DealerBustTest {

    @Test
    void 딜러의_카드_점수_합이_16_이하_일_때_딜러의_카드_점수_합이_17_이상_21초과가_될_때까지_카드를_더_받는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.TEN));
        dealer.addCard(new Card(CardShape.HEART, CardValue.SIX));

        // then
        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    void 딜러의_카드_점수_합이_17_이상_일_때_딜러는_카드를_더_받지_못한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardShape.HEART, CardValue.TEN));
        dealer.addCard(new Card(CardShape.HEART, CardValue.NINE));
        dealer.addCard(new Card(CardShape.HEART, CardValue.EIGHT));

        // then
        assertThat(dealer.canHit()).isFalse();
    }
}
