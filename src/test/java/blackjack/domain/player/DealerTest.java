package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import blackjack.domain.player.strategy.AllCardsOpenStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("딜러 생성 테스트")
    @Test
    void createDealer() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getName()).isEqualTo(new Name("딜러"));
        assertThatThrownBy(dealer::getCards).isInstanceOf(IndexOutOfBoundsException.class);

        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCards()).isEmpty();
    }

    @DisplayName("카드 합이 16 이하이면 isCanDraw()가 true - 0일 때")
    @Test
    void canDrawCardWhen0() {
        Dealer dealer = new Dealer();
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드 합이 16 이하이면 반드시 isCanDraw()가 true - 16일 때")
    @Test
    void canDrawCardWhen16() {
        Dealer dealer = new Dealer();
        Card eightCard = new Card(CardShapeType.DIAMOND, CardNumberType.EIGHT);
        dealer.drawOneCard(eightCard);
        dealer.drawOneCard(eightCard);
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드 합이 17 이상이면 isCanDraw()가 false - 17일 때")
    @Test
    void cannotDrawCardWhen17() {
        Dealer dealer = new Dealer();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card sevenCard = new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN);
        dealer.drawOneCard(tenCard);
        dealer.drawOneCard(sevenCard);
        assertThat(dealer.isCanDraw()).isFalse();
    }
}
