package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
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
        Card eightCard = new Card(CardShape.DIAMOND, CardNumber.EIGHT);
        dealer.drawCard(eightCard);
        dealer.drawCard(eightCard);
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드 합이 17 이상이면 카드를 추가로 받을 수 없다. - 17일 때")
    @Test
    void cannotDrawCardWhen17() {
        Dealer dealer = new Dealer();
        Card tenCard = new Card(CardShape.DIAMOND, CardNumber.TEN);
        Card sevenCard = new Card(CardShape.DIAMOND, CardNumber.SEVEN);
        dealer.drawCard(tenCard);
        dealer.drawCard(sevenCard);
        assertThat(dealer.isCanDraw()).isFalse();
    }

    @DisplayName("카드 반환 개수 전략패턴 테스트")
    @Test
    void cardOpenStrategy() {
        Dealer dealer = new Dealer();
        dealer.drawRandomTwoCards();
        assertThat(dealer.getCardsWithStrategy().size()).isEqualTo(1);
        dealer.setCardOpenStrategy(new AllCardsOpenStrategy());
        assertThat(dealer.getCardsWithStrategy().size()).isEqualTo(2);
    }
}