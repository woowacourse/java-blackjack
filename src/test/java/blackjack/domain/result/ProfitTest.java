package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Number;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    private Participants participants;
    private Profit profit;

    @BeforeEach
    void setUp() {
        Players players = Players.from(List.of("a", "b"));
        Dealer dealer = new Dealer(new Participant(Hand.generateEmptyCards()));
        participants = new Participants(dealer, players);
        profit = new Profit(participants);
        bettingMoney();
        settingPlayer2();
    }

    private void bettingMoney() {
        participants.getPlayers().get(0).betting(10000);
        participants.getPlayers().get(1).betting(20000);
    }

    private void settingPlayer2() {
        // player2 점수 : 20
        participants.getPlayers().get(1).receiveCard(new Card(Number.K, Pattern.HEART));
        participants.getPlayers().get(1).receiveCard(new Card(Number.TEN, Pattern.HEART));
    }

    @Test
    @DisplayName("플레이어들의 수익을 계산한다 - 플레이어와 딜러 모두 블랙잭인 경우")
    void makePlayersProfit_blackjack() {
        // player1 점수 : 21 (블랙잭)
        participants.getPlayers().get(0).receiveCard(new Card(Number.ACE, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        // dealer 점수 : 21 (블랙잭)
        participants.getDealer().receiveCard(new Card(Number.ACE, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        assertThat(playersProfit.get(participants.getPlayers().get(0))).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("플레이어들의 수익을 계산한다 - 플레이어만 블랙잭인 경우")
    void makePlayersProfit_playerBlackjack() {
        // player1 점수 : 21 (블랙잭)
        participants.getPlayers().get(0).receiveCard(new Card(Number.ACE, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        // dealer 점수 : 19
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.NINE, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        assertThat(playersProfit.get(participants.getPlayers().get(0))).isEqualTo(new Money(15000));
    }

    @Test
    @DisplayName("플레이어들의 수익을 계산한다 - 플레이어가 버스트인 경우")
    void makePlayersProfit_playerBust() {
        // player1 점수 : 27 (버스트)
        participants.getPlayers().get(0).receiveCard(new Card(Number.EIGHT, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.NINE, Pattern.CLUB));
        // dealer 점수 : 19
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.NINE, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        assertThat(playersProfit.get(participants.getPlayers().get(0))).isEqualTo(new Money(-10000));
    }

    @Test
    @DisplayName("플레이어들의 수익을 계산한다 - 딜러가 버스트인 경우")
    void makePlayersProfit_dealerBust() {
        // player1 점수 : 18
        participants.getPlayers().get(0).receiveCard(new Card(Number.EIGHT, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        // dealer 점수 : 24 (버스트)
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.SIX, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.EIGHT, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        assertThat(playersProfit.get(participants.getPlayers().get(0))).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("플레이어들의 수익을 계산한다 - 딜러와 점수가 같은 경우")
    void makePlayersProfit_sameScore() {
        // player1 점수 : 18
        participants.getPlayers().get(0).receiveCard(new Card(Number.EIGHT, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        // dealer 점수 : 18
        participants.getDealer().receiveCard(new Card(Number.EIGHT, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        assertThat(playersProfit.get(participants.getPlayers().get(0))).isEqualTo(new Money(0));
    }

    @Test
    @DisplayName("딜러의 수익을 계산한다")
    void getDealerProfit() {
        // player1 점수 : 27 (버스트)
        participants.getPlayers().get(0).receiveCard(new Card(Number.EIGHT, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.TEN, Pattern.CLUB));
        participants.getPlayers().get(0).receiveCard(new Card(Number.NINE, Pattern.CLUB));
        // dealer 점수 : 19
        participants.getDealer().receiveCard(new Card(Number.TEN, Pattern.DIAMOND));
        participants.getDealer().receiveCard(new Card(Number.NINE, Pattern.DIAMOND));

        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        Money dealerProfit = profit.getDealerProfit(playersProfit);
        assertThat(dealerProfit).isEqualTo(new Money(-10000));
    }
}
