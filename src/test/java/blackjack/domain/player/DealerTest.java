package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import blackjack.domain.card.Cards;
import blackjack.domain.player.strategy.AllCardsOpenStrategy;
import blackjack.domain.player.strategy.OneCardOpenStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("카드 합이 16 이하이면 반드시 1장의 카드를 추가로 받는다. - 0일 때")
    @Test
    void canDrawCardWhen0() {
        Dealer dealer = new Dealer();
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드 합이 16 이하이면 반드시 1장의 카드를 추가로 받는다. - 16일 때")
    @Test
    void canDrawCardWhen16() {
        Dealer dealer = new Dealer();
        Card eightCard = new Card(CardShapeType.DIAMOND, CardNumberType.EIGHT);
        dealer.drawOneCard(eightCard);
        dealer.drawOneCard(eightCard);
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드 합이 17 이상이면 카드를 추가로 받을 수 없다. - 17일 때")
    @Test
    void cannotDrawCardWhen17() {
        Dealer dealer = new Dealer();
        Card tenCard = new Card(CardShapeType.DIAMOND, CardNumberType.TEN);
        Card sevenCard = new Card(CardShapeType.DIAMOND, CardNumberType.SEVEN);
        dealer.drawOneCard(tenCard);
        dealer.drawOneCard(sevenCard);
        assertThat(dealer.isCanDraw()).isFalse();
    }

    @DisplayName("카드 반환 개수 전략패턴 테스트")
    @Test
    void cardOpenStrategy() {
        Cards allCards = new Cards();
        Dealer dealer = new Dealer();

        dealer.drawRandomTwoCards(allCards);
        assertThat(dealer.getCards().size()).isEqualTo(1);

        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(2);

        dealer.setCardOpenStrategy(new OneCardOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(1);

        dealer.drawRandomTwoCards(allCards);

        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(4);

        dealer.setCardOpenStrategy(new OneCardOpenStrategy());
        assertThat(dealer.getCards().size()).isEqualTo(1);
    }
}
