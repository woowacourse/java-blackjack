package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    private final GameResultFixture gameResultFixture = new GameResultFixture();

    @Test
    void 플레이어가_버스트이면_딜러_결과에_승이_집계된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLOVER, Rank.TEN));

        GameResults results = GameResults.create(new Players(List.of(player)), dealer);

        assertThat(results.dealerResult().get(GameResult.WIN)).isEqualTo(1);
    }

    @Test
    void 플레이어가_승리하면_딜러_패수에_1이_추가되고_해당_플레이어_결과는_승이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");

        GameResults gameResults = gameResultFixture.플레이어가_딜러에게_이기는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.LOSE)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어가_패배하면_딜러_승수에_1이_추가되고_해당_플레이어_결과는_패이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = gameResultFixture.플레이어가_딜러에게_지는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.WIN)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어가_비기면_딜러_무승부수에_1이_추가되고_해당_플레이어_결과는_무승부이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = gameResultFixture.플레이어가_딜러에게_비기는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.DRAW)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어가_지면_베팅금액만큼_잃고_딜러_수익에_그만큼_합산된다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = gameResultFixture.플레이어가_딜러에게_지는_게임_결과(player, dealer);

        int wager = 10000;
        Map<Player, Integer> wagers = new HashMap<>();
        wagers.put(player, wager);

        Map<Participant, Integer> profits = gameResults.calculateProfits(wagers, dealer);

        assertThat(profits.get(player)).isEqualTo(-wager);
        assertThat(profits.get(dealer)).isEqualTo(wager);
    }

    @Test
    void 플레이어가_이기면_베팅금액만큼_얻고_딜러_수익에서_그만큼_차감된다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = gameResultFixture.플레이어가_딜러에게_이기는_게임_결과(player, dealer);

        int wager = 10000;
        Map<Player, Integer> wagers = new HashMap<>();
        wagers.put(player, wager);

        Map<Participant, Integer> profits = gameResults.calculateProfits(wagers, dealer);

        assertThat(profits.get(player)).isEqualTo(wager);
        assertThat(profits.get(dealer)).isEqualTo(-wager);
    }

    @Test
    void 플레이어_중에_한_명이_비기면_딜러_수익에_합산도_차감도_되지_않는다() {
        Dealer dealer = new Dealer();
        Player loser = new Player("loser");
        Player drawer = new Player("drawer");

        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        loser.receiveCard(new Card(Suit.HEART, Rank.TEN));
        loser.receiveCard(new Card(Suit.SPADE, Rank.SIX));

        drawer.receiveCard(new Card(Suit.CLOVER, Rank.TEN));
        drawer.receiveCard(new Card(Suit.CLOVER, Rank.SEVEN));

        GameResults results = GameResults.create(new Players(List.of(loser, drawer)), dealer);

        Map<Player, Integer> wagers = new HashMap<>();
        int loserWager = 10000;
        int drawerWager = 20000;
        wagers.put(loser, loserWager);
        wagers.put(drawer, drawerWager);

        Map<Participant, Integer> profits = results.calculateProfits(wagers, dealer);

        assertThat(profits.get(loser)).isEqualTo(-loserWager);
        assertThat(profits.get(drawer)).isZero();
        assertThat(profits.get(dealer)).isEqualTo(loserWager);
    }
}
