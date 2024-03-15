package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Rank;
import blackjack.domain.deck.DeckGenerator;
import blackjack.domain.deck.PlayingDeck;
import blackjack.domain.deck.shuffle.NoShuffle;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameBettingManagerTest {

    @DisplayName("딜러와 플레이어가 둘 다 버스트면 플레이어가 배팅 금액을 모두 잃는다.")
    @Test
    void calculateBattingReward_allBust() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(1000),
                () -> assertThat(playerResult).isEqualTo(-1000)
        );
    }

    @DisplayName("딜러만 버스트라면 플레이어가 1배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_DealerBust() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(-1000),
                () -> assertThat(playerResult).isEqualTo(1000)
        );
    }

    @DisplayName("플레이어만 블랙잭이면 플레이어가 1.5배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_PlayerBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

         //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(-1500),
                () -> assertThat(playerResult).isEqualTo(1500)
        );
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 플레이어가 1배의 배팅 금액을 돌려받는다.")
    @Test
    void calculateBattingReward_allBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(0),
                () -> assertThat(playerResult).isEqualTo(0)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트와 블랙잭이 아닐 경우 더 큰 점수가 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(1000),
                () -> assertThat(playerResult).isEqualTo(-1000)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트나 블랙잭이 아닐 때 점수가 같으면 플레이어가 1배의 금액을 돌려받는다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack_sameScore() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        PlayingDeck playingDeck = new PlayingDeck(DeckGenerator.generateDeck(), new NoShuffle());

        Dealer dealer = new Dealer(playingDeck);
        Player player = new Player("test");
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.NINE));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        double dealerResult = gameBettingManager.getDealerResult();
        double playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(0),
                () -> assertThat(playerResult).isEqualTo(0)
        );
    }

}
