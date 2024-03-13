package blackjack.domain.player;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.dealer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerTest {
    @Test
    void 합계가_16이하라면_카드가_더_필요하다() {
        Dealer dealer = dealer(
                new Card(KING, SPADE),
                new Card(SIX, SPADE));

        assertThat(dealer.isMoreCardNeeded()).isTrue();
    }

    @Test
    void 합계가_16보다_크다면_카드가_더_필요하다() {
        Dealer dealer = dealer(
                new Card(KING, SPADE),
                new Card(SEVEN, SPADE));

        assertThat(dealer.isMoreCardNeeded()).isFalse();
    }

    @Test
    void 딜러에게_카드가_있는_경우에_첫_카드를_요청하면_정상적으로_돌려준다() {
        Dealer dealer = dealer(new Card(ACE, SPADE));

        assertThat(dealer.getFirstCard()).isNotNull();
    }

    @Test
    void 딜러에게_카드가_없는_경우에_첫_카드를_요청하면_예외가_발생한다() {
        Dealer dealer = dealer();

        assertThatThrownBy(dealer::getFirstCard)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
    }
}
