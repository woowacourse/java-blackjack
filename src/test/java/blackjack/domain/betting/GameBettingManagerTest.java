package blackjack.domain.betting;

import blackjack.domain.card.Card;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Rank;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameBettingManagerTest {

    @DisplayName("딜러와 플레이어가 둘 다 버스트면 플레이어가 배팅 금액을 모두 잃는다.")
    @Test
    void calculateBattingReward_allBust() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.TEN),
                new Card(Pattern.HEART, Rank.TEN));

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards);
        Betting betting = new Betting("1000.0");

        gameBettingManager.registerPlayerBetting(player, betting);

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(1000));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(-1000));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

    @DisplayName("딜러만 버스트라면 플레이어가 1배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_DealerBust() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.TEN)
        );

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards);
        Betting betting = new Betting("1000.0");

        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(-1000));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(1000));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

    @DisplayName("플레이어만 블랙잭이면 플레이어가 1.5배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_PlayerBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.ACE)
        );

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards);
        Betting betting = new Betting("1000.0");

        gameBettingManager.registerPlayerBetting(player, betting);

        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(-1500));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(1500));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 플레이어가 1배의 배팅 금액을 돌려받는다.")
    @Test
    void calculateBattingReward_allBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.ACE)
        );

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards);
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(0));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(0));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트와 블랙잭이 아닐 경우 더 큰 점수가 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.NINE)
        );

        List<Card> cards2 = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.EIGHT)
        );

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards2);
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(1000));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(-1000));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트나 블랙잭이 아닐 때 점수가 같으면 플레이어가 1배의 금액을 돌려받는다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack_sameScore() {
        //given
        GameBettingManager gameBettingManager = new GameBettingManager();
        List<Card> cards = List.of(
                new Card(Pattern.SPADE, Rank.TEN),
                new Card(Pattern.CLOVER, Rank.NINE)
        );

        Dealer dealer = new Dealer(cards);
        Player player = new Player("test", cards);
        Betting betting = new Betting("1000.0");
        gameBettingManager.registerPlayerBetting(player, betting);

        //when
        gameBettingManager.calculatePlayerProfit(dealer, player);

        BigDecimal dealerResult = gameBettingManager.getDealerResult();
        BigDecimal playerResult = gameBettingManager.getPlayersResult().get(player).getBettingMoney();

        int dealerCompareResult = dealerResult.compareTo(BigDecimal.valueOf(0));
        int playerCompareResult = playerResult.compareTo(BigDecimal.valueOf(0));

        //then
        assertAll(
                () -> assertThat(dealerCompareResult).isEqualTo(0),
                () -> assertThat(playerCompareResult).isEqualTo(0)
        );
    }

}
