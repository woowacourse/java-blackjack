package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.result.UserResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    @DisplayName("Players가 받을 금액을 계산한다.")
    @Test
    void player_blackjack_dealer_not_blackjack() {
        // given
        Players players = Players.from(new Names(List.of("merry")));
        Dealer dealer = new Dealer();
        Users users = new Users(dealer, players);
        Player merry = players.getPlayers().get(0);
        merry.setBetAmount(10000);

        merry.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        merry.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.TWO, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);

        users.calculateReceivingAmount(userResults);

        assertThat(merry.getReceivingAmount())
                .isEqualTo(10000 * 1.5);
    }

    @DisplayName("Players가 받을 금액을 계산한다.")
    @Test
    void player_blackjack_dealer_blackjack() {
        // given
        Players players = Players.from(new Names(List.of("merry")));
        Dealer dealer = new Dealer();
        Users users = new Users(dealer, players);
        Player merry = players.getPlayers().get(0);
        merry.setBetAmount(10000);

        merry.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        merry.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);

        users.calculateReceivingAmount(userResults);

        assertThat(merry.getReceivingAmount())
                .isEqualTo(10000);
    }

    @DisplayName("Players가 받을 금액을 계산한다.")
    @Test
    void calculate_dealer_receiving_amount() {
        // given
        Players players = Players.from(new Names(List.of("merry")));
        Dealer dealer = new Dealer();
        Users users = new Users(dealer, players);
        Player merry = players.getPlayers().get(0);
        merry.setBetAmount(10000);

        merry.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        merry.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);

        users.calculateReceivingAmount(userResults);

        assertThat(users.calculateDealerReceivingAmount())
                .isEqualTo(0);
    }

    @DisplayName("Players가 받을 금액을 계산한다.")
    @Test
    void player_blackjack_dealer_not_blackjack_calculate_dealer() {
        // given
        Players players = Players.from(new Names(List.of("merry")));
        Dealer dealer = new Dealer();
        Users users = new Users(dealer, players);
        Player merry = players.getPlayers().get(0);
        merry.setBetAmount(10000);

        merry.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        merry.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.TWO, CardSymbol.HEART));

        assertThat(users.calculateDealerReceivingAmount())
                .isEqualTo(-5000);
    }
}