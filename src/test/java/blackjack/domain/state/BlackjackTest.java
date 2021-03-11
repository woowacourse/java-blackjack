package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackTest {
    private static final String TEST_NAME = "mungto";

    @DisplayName("딜러가 블랙잭일때 이득 테스트")
    @Test
    void profitWhenDealerBlackjack() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);

        Dealer dealer = new Dealer(aceCard, tenCard);

        User user = new User(TEST_NAME, aceCard, tenCard);
        user.setBetAmount("10000");

        assertThat(user.profit(dealer)).isEqualTo(0);
    }

    @DisplayName("딜러가 stay 일때 이득 테스트")
    @Test
    void profitWhenDealerStay() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);

        Dealer dealer = new Dealer(tenCard, tenCard);

        User user = new User(TEST_NAME, aceCard, tenCard);
        user.setBetAmount("10000");

        assertThat(user.profit(dealer)).isEqualTo(15000);
    }

    @DisplayName("딜러가 bust 일때 이득 테스트")
    @Test
    void profitWhenDealerBust() {
        Card aceCard = Card.valueOf(CardShape.CLUB, CardNumber.ACE);
        Card tenCard = Card.valueOf(CardShape.CLUB, CardNumber.TEN);

        Dealer dealer = new Dealer(aceCard, aceCard);
        dealer.drawCard(tenCard);

        User user = new User(TEST_NAME, aceCard, tenCard);
        user.setBetAmount("10000");

        assertThat(user.profit(dealer)).isEqualTo(15000);
    }
}