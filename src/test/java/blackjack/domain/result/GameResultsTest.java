package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.fixture.GameResultFixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    @Test
    void 플레이어가_버스트이면_딜러_결과에_승이_집계된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.TEN));
        player.receiveCard(new Card(Suit.CLUB, Rank.TEN));

        GameResults results = GameResults.create(new Players(List.of(player)), dealer);

        assertThat(results.dealerResult().get(GameResult.WIN)).isEqualTo(1);
    }

    @Test
    void 플레이어가_승리하면_딜러_패수에_1이_추가되고_해당_플레이어_결과는_승이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");

        GameResults gameResults = GameResultFixture.플레이어가_딜러에게_이기는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.LOSE)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 플레이어가_패배하면_딜러_승수에_1이_추가되고_해당_플레이어_결과는_패이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = GameResultFixture.플레이어가_딜러에게_지는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.WIN)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 플레이어가_비기면_딜러_무승부수에_1이_추가되고_해당_플레이어_결과는_무승부이다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = GameResultFixture.플레이어가_딜러에게_비기는_게임_결과(player, dealer);

        assertThat(gameResults.dealerResult().get(GameResult.DRAW)).isEqualTo(1);
        assertThat(gameResults.playerResults().get(player)).isEqualTo(GameResult.DRAW);
    }
}
