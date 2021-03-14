package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Value;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {
    @DisplayName("유저의 블랙잭 승, 승, 무, 패 구하는 테스트")
    @Test
    void testDecideProfit() {
        Dealer dealer = new Dealer();

        dealer.drawCard(new Card(Suit.DIAMOND, Value.JACK));
        dealer.drawCard(new Card(Suit.SPADE, Value.EIGHT));

        User user1 = new User(Name.from("무"));
        User user2 = new User(Name.from("블랙잭 승"));
        User user3 = new User(Name.from("승"));
        User user4 = new User(Name.from("패"));

        user1.drawCard((new Card(Suit.DIAMOND, Value.KING)));
        user1.drawCard((new Card(Suit.HEART, Value.EIGHT)));

        user2.drawCard(new Card(Suit.DIAMOND, Value.KING));
        user2.drawCard(new Card(Suit.SPADE, Value.ACE));

        user3.drawCard(new Card(Suit.DIAMOND, Value.EIGHT));
        user3.drawCard(new Card(Suit.SPADE, Value.ACE));

        user4.drawCard(new Card(Suit.DIAMOND, Value.TWO));
        user4.drawCard(new Card(Suit.SPADE, Value.FIVE));

        assertThat(Profit.decideProfit(user1, dealer)).isEqualTo(Profit.DRAW);
        assertThat(Profit.decideProfit(user2, dealer)).isEqualTo(Profit.BLACKJACK_WIN);
        assertThat(Profit.decideProfit(user3, dealer)).isEqualTo(Profit.WIN);
        assertThat(Profit.decideProfit(user4, dealer)).isEqualTo(Profit.LOSE);
    }
}