package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러를_생성한다() {
        // given
        Dealer dealer = new Dealer();

        // when & then
        assertThat(dealer).isNotNull();
    }

    @Test
    void 딜러에게_카드를_한장_준다() {
        // Given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));

        // When & Then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러가_카드를_더_뽑아야_하는_상황을_검사한다() {
        // Given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));

        // When & Then
        assertThat(dealer.isCardDrawable()).isTrue();
    }

    @Test
    void 딜러가_카드를_더_뽑을_수_없는_상황을_검사한다() {
        // Given
        Dealer dealer = new Dealer();
        dealer.putCard(new Card(CardShape.CLOVER, CardType.NORMAL_8));
        dealer.putCard(new Card(CardShape.CLOVER, CardType.KING));

        // When & Then
        assertThat(dealer.isCardDrawable()).isFalse();
    }
}
