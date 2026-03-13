package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.shuffler.RandomCardShuffler;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("플레이어가 버스트이면 패이다")
    void of_returnsLose_whenPlayerIsBust() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.EIGHT));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLOVER, Rank.THREE));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 버스트이면 승이다")
    void of_returnsWin_whenDealerIsBust() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.THREE));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.EIGHT));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승이다")
    void of_returnsWin_whenPlayerScoreIsHigher() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패이다")
    void of_returnsLose_whenPlayerScoreIsLower() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어 점수와 딜러 점수가 같으면 무승부이다")
    void of_returnsDraw_whenScoresAreEqual() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.EIGHT));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.EIGHT));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이면 블랙잭이다")
    void of_returnsBlackjack_whenOnlyPlayerHasBlackjack() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.EIGHT));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.HEART, Rank.ACE));
        player.receiveCard(new Card(Suit.SPADE, Rank.KING));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭이면 무승부이다")
    void of_returnsDraw_whenBothHaveBlackjack() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.ACE));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.KING));

        Player player = new Player(new Name("pobi"), new BettingMoney(1000));
        player.receiveCard(new Card(Suit.CLOVER, Rank.ACE));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.KING));

        // when
        GameResult result = GameResult.of(player, dealer);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("블랙잭이면 배팅 금액의 1.5배를 수익으로 받는다")
    void calculateProfit_returnsOnePointFiveTimes_whenBlackjack() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        int profit = GameResult.BLACKJACK.calculateProfit(bettingMoney).getAmount();

        // then
        assertThat(profit).isEqualTo(15000);
    }

    @Test
    @DisplayName("승이면 배팅 금액과 동일한 수익을 받는다")
    void calculateProfit_returnsFullAmount_whenWin() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        int profit = GameResult.WIN.calculateProfit(bettingMoney).getAmount();

        // then
        assertThat(profit).isEqualTo(10000);
    }

    @Test
    @DisplayName("무승부이면 수익이 0이다")
    void calculateProfit_returnsZero_whenDraw() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        int profit = GameResult.DRAW.calculateProfit(bettingMoney).getAmount();

        // then
        assertThat(profit).isZero();
    }

    @Test
    @DisplayName("패이면 배팅 금액만큼 잃는다")
    void calculateProfit_returnsNegativeAmount_whenLose() {
        // given
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        int profit = GameResult.LOSE.calculateProfit(bettingMoney).getAmount();

        // then
        assertThat(profit).isEqualTo(-10000);
    }
}
