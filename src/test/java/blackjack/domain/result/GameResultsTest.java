package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.shuffler.RandomCardShuffler;
import blackjack.domain.hand.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultsTest {

    @Test
    @DisplayName("플레이어가 승리하면 플레이어 수익은 배팅 금액과 같다")
    void calculate_playerProfitEqualsAmount_whenPlayerWins() {
        // given
        Dealer dealer = new Dealer(new Hand(), new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player(new Name("pobi"), new Hand(), new BettingMoney(10000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        // when
        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        // then
        assertThat(results.getProfitsByPlayer().get(player).getAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 패배하면 플레이어 수익은 배팅 금액의 음수이다")
    void calculate_playerProfitIsNegative_whenPlayerLoses() {
        // given
        Dealer dealer = new Dealer(new Hand(), new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        Player player = new Player(new Name("pobi"), new Hand(), new BettingMoney(10000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        // when
        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        // then
        assertThat(results.getProfitsByPlayer().get(player).getAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 무승부이면 플레이어 수익은 0이다")
    void calculate_playerProfitIsZero_whenDraw() {
        // given
        Dealer dealer = new Dealer(new Hand(), new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player(new Name("pobi"), new Hand(), new BettingMoney(10000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        // when
        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        // then
        assertThat(results.getProfitsByPlayer().get(player).getAmount()).isZero();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 플레이어 수익은 배팅 금액의 1.5배이다")
    void calculate_playerProfitIsOnePointFiveTimes_whenBlackjack() {
        // given
        Dealer dealer = new Dealer(new Hand(), new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player(new Name("pobi"), new Hand(), new BettingMoney(10000));
        player.receiveCard(new Card(Suit.HEART, Rank.ACE));
        player.receiveCard(new Card(Suit.SPADE, Rank.KING));

        // when
        GameResults results = GameResults.calculate(new Players(List.of(player)), dealer);

        // then
        assertThat(results.getProfitsByPlayer().get(player).getAmount()).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러 수익은 플레이어 수익의 합산을 반전한 값이다")
    void calculate_dealerProfitIsNegativeSumOfPlayerProfits() {
        // given
        Dealer dealer = new Dealer(new Hand(), new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player winner = new Player(new Name("pobi"), new Hand(), new BettingMoney(10000));
        winner.receiveCard(new Card(Suit.HEART, Rank.TEN));
        winner.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        Player loser = new Player(new Name("jason"), new Hand(), new BettingMoney(20000));
        loser.receiveCard(new Card(Suit.CLOVER, Rank.TEN));
        loser.receiveCard(new Card(Suit.DIAMOND, Rank.SIX));

        // when
        GameResults results = GameResults.calculate(new Players(List.of(winner, loser)), dealer);

        // then
        assertThat(results.getDealerProfit().getAmount()).isEqualTo(10000);
    }
}
