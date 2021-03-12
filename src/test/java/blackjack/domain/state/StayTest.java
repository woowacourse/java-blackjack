package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StayTest {
    private static final String TEST_NAME = "mungto";

    @DisplayName("딜러가 블랙잭일때 이득 테스트")
    @Test
    void profitWhenDealerBlackjack() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);

        Dealer dealer = new Dealer(aceCard, tenCard);

        User user = new User(TEST_NAME, aceCard, aceCard);
        user.setBetAmount("10000");
        user.stay();

        assertThat(user.profit(dealer)).isEqualTo(-10000);
    }

    @DisplayName("딜러가 stay 이고 내 점수가 높을때 이득 테스트")
    @Test
    void profitWhenDealerStayAndMyWin() {
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);
        Card nineCard = Card.valueOf(CardShape.CLUB, CardNumber.NINE);

        Dealer dealer = new Dealer(nineCard, nineCard);

        User user = new User(TEST_NAME, tenCard, tenCard);
        user.setBetAmount("10000");
        user.stay();

        assertThat(user.profit(dealer)).isEqualTo(10000);
    }

    @DisplayName("딜러가 stay 이고 내 점수가 낮을때 이득 테스트")
    @Test
    void profitWhenDealerStayAndMyLose() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card nineCard = Card.valueOf(CardShape.CLUB, CardNumber.NINE);

        Dealer dealer = new Dealer(nineCard, nineCard);

        User user = new User(TEST_NAME, aceCard, aceCard);
        user.setBetAmount("10000");
        user.stay();

        assertThat(user.profit(dealer)).isEqualTo(-10000);
    }

    @DisplayName("딜러가 bust 일때 이득 테스트")
    @Test
    void profitWhenDealerBust() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card eightCard = Card.valueOf(CardShape.CLUB, CardNumber.EIGHT);
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);

        Dealer dealer = new Dealer(eightCard, eightCard);
        dealer.drawCard(tenCard);

        User user = new User(TEST_NAME, aceCard, aceCard);
        user.setBetAmount("10000");
        user.stay();

        assertThat(user.profit(dealer)).isEqualTo(10000);
    }
}