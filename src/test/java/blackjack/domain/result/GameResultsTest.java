package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultsTest {

    @Test
    void 플레이어가_승리하면_딜러_결과에_패가_1개_집계된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        GameResults results = GameResults.create(new Players(List.of(player)), dealer);

        assertThat(results.dealerResult().get(GameResult.LOSE)).isEqualTo(1);
    }

    @Test
    void 플레이어가_패배하면_딜러_결과에_승이_1개_집계된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.NINE));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        GameResults results = GameResults.create(new Players(List.of(player)), dealer);

        assertThat(results.dealerResult().get(GameResult.WIN)).isEqualTo(1);
    }

    @Test
    void 점수가_같으면_딜러_결과에_무승부가_1개_집계된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        Player player = new Player("pobi");
        player.receiveCard(new Card(Suit.HEART, Rank.TEN));
        player.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        GameResults results = GameResults.create(new Players(List.of(player)), dealer);

        assertThat(results.dealerResult().get(GameResult.DRAW)).isEqualTo(1);
    }
}
