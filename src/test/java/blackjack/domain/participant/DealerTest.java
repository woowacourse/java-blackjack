package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_점수가_17미만이면_카드를_더_받아야_한다() {
        // given
        Dealer dealer = new Dealer();
        dealer.bring(new Card(CardValue.SIX, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when & then
        assertThat(dealer.shouldDrawCard()).isTrue();
    }

    @Test
    void 딜러_점수가_17이상이면_카드를_받지_않는다() {
        // given
        Dealer dealer = new Dealer();
        dealer.bring(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.bring(new Card(CardValue.TEN, CardShape.DIAMOND));

        // when & then
        assertThat(dealer.shouldDrawCard()).isFalse();
    }


}
