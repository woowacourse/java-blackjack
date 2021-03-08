package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.cardopen.AllCardsOpenStrategy;
import blackjack.domain.player.cardopen.OneCardOpenStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 0일 때 true")
    @Test
    void canDrawCardWhen0() {
        Dealer dealer = new Dealer();
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 16일 때 true")
    @Test
    void canDrawCardWhen16() {
        Dealer dealer = new Dealer();
        Card eightCard = Card.valueOf(CardShape.DIAMOND, CardNumber.EIGHT);
        dealer.drawCard(eightCard);
        dealer.drawCard(eightCard);
        assertThat(dealer.isCanDraw()).isTrue();
    }

    @DisplayName("카드를 추가로 받을수 있는지 가능여부 테스트 - 17일 때 false")
    @Test
    void cannotDrawCardWhen17() {
        Dealer dealer = new Dealer();
        Card tenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.TEN);
        Card sevenCard = Card.valueOf(CardShape.DIAMOND, CardNumber.SEVEN);
        dealer.drawCard(tenCard);
        dealer.drawCard(sevenCard);
        assertThat(dealer.isCanDraw()).isFalse();
    }

    @DisplayName("카드 반환 개수 전략패턴 적용 테스트 - 1개")
    @Test
    void oneCardOpenStrategy() {
        Dealer dealer = new Dealer();
        dealer.setCardOpen(new OneCardOpenStrategy());
        dealer.drawRandomTwoCards();
        assertThat(dealer.getCards()).hasSize(1);
    }

    @DisplayName("카드 반환 개수 전략패턴 적용 테스트 - 2개")
    @Test
    void twoCardOpenStrategy() {
        Dealer dealer = new Dealer();
        dealer.setCardOpen(new AllCardsOpenStrategy());
        dealer.drawRandomTwoCards();
        assertThat(dealer.getCards()).hasSize(2);
    }
}