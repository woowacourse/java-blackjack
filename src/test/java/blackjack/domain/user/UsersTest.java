package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.result.UserResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    private Users users;
    private Players players;
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        this.players = Players.from(new Names(List.of("merry")));
        this.dealer = new Dealer();
        this.users = new Users(dealer, players);
        player = players.getPlayers().get(0);
        player.setBetAmount(10000);
    }


    @DisplayName("Dealer가 블랙잭이 아닌 경우, Player가 블랙잭이라면 Player는 베팅 금액의 1.5배를 반환한다.")
    @Test
    void player_blackjack_dealer_not_blackjack() {
        // when
        player.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.TWO, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);
        users.calculateRewards(userResults);

        // then
        assertThat(player.getReceivingAmount())
                .isEqualTo(10000 * 1.5);
    }

    @DisplayName("Dealer가 블랙잭인 경우, Player가 블랙잭이라면 Player는 베팅 금액을 받는다.")
    @Test
    void player_blackjack_dealer_blackjack() {
        // when
        player.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);

        users.calculateRewards(userResults);

        // then
        assertThat(player.getReceivingAmount())
                .isEqualTo(10000);
    }

    @DisplayName("Dealer와 Player 모두 블랙잭으로 무승부라면 Dealer는 Player의 베팅 금액 만큼의 손해를 본다.")
    @Test
    void calculate_dealer_rewards_when_dealer_tie() {
        // given
        player.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        UserResults userResults = UserResults.of(users);

        users.calculateRewards(userResults);

        assertThat(users.calculateDealerRewards())
                .isEqualTo(-10000);
    }

    @DisplayName("Player가 블랙잭이고 Dealer가 블랙잭이 아닌 경우 Dealer는 Player의 베팅 금액 만큼 손해를 본다.")
    @Test
    void calculate_dealer_rewards_when_dealer_lose() {
        player.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        player.updateCardScore(new Card(CardNumber.KING, CardSymbol.HEART));

        dealer.updateCardScore(new Card(CardNumber.ACE, CardSymbol.HEART));
        dealer.updateCardScore(new Card(CardNumber.TWO, CardSymbol.HEART));

        assertThat(users.calculateDealerRewards())
                .isEqualTo(-10000);
    }
}