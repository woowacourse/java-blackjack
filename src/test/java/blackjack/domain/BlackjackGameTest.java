package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("처음에 플레이어와 딜러는 각자 카드 2장씩 받는다.")
    @Test
    void test_distributeInitialCards() {
        // given
        Player player = TestUtil.createPlayerFromName("player");

        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardHand());
        BlackjackGame game = new BlackjackGame(players, dealer, CardDeck.createShuffledDeck());

        // when
        game.distributeInitialCards();

        // then
        assertThat(player.getCardSize()).isEqualTo(2);
        assertThat(dealer.getCardSize()).isEqualTo(2);
    }

    @DisplayName("딜러는 16이상이 될때까지 카드를 자동으로 더 받는다.")
    @Test
    void test_dealerTurn() {
        // given
        Player player = TestUtil.createPlayerFromName("player");

        List<Player> players = List.of(player);
        Dealer dealer = new Dealer(new CardHand());
        BlackjackGame game = new BlackjackGame(players, dealer, CardDeck.createShuffledDeck());

        // when
        game.dealerTurn();

        // then
        int score = dealer.getScore().intValue();
        assertThat(score).isGreaterThan(16);
    }

    @DisplayName("플레이어들의 수익을 각각 계산한다.")
    @Test
    void testDealerProfit_playersProfit() {
        // given
        CardHand playerHand= new CardHand();
        playerHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        playerHand.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 20

        int player1Bet = 10000;
        Player player1 = TestUtil.createPlayerOf(playerHand, player1Bet);

        int player2Bet = 5000;
        Player player2 = TestUtil.createPlayerOf(playerHand, player2Bet);

        CardHand dealerHand = new CardHand();
        dealerHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        dealerHand.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 21
        Dealer dealer = new Dealer(dealerHand);

        List<Player> players = List.of(player1, player2);

        // when
        BlackjackGame game = new BlackjackGame(players, dealer, CardDeck.createShuffledDeck());
        Map<Player, Integer> playersProfit = game.calculatePlayersProfit();

        // then
        assertThat(playersProfit.get(player1)).isEqualTo(-10000);
        assertThat(playersProfit.get(player2)).isEqualTo(-5000);
    }

    @DisplayName("딜러의 수익은 플레이어수익 합의 반대이다")
    @Test
    void testDealerProfit_playersProfitSumReverse() {
        // given
        CardHand playerHand= new CardHand();
        playerHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        playerHand.add(new Card(CardSuit.CLUB, CardRank.JACK)); // 20

        int player1Bet = 10000;
        Player player1 = TestUtil.createPlayerOf(playerHand, player1Bet);

        int player2Bet = 5000;
        Player player2 = TestUtil.createPlayerOf(playerHand, player2Bet);

        CardHand dealerHand = new CardHand();
        dealerHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        dealerHand.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 21
        Dealer dealer = new Dealer(dealerHand);

        List<Player> players = List.of(player1, player2);

        // when
        BlackjackGame game = new BlackjackGame(players, dealer, CardDeck.createShuffledDeck());
        int dealerProfit = game.calculateDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(15000);
    }

}