package participant;

import constant.Suit;
import constant.Rank;
import constant.WinningResult;
import game.Card;
import game.Cards;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayersTest {

    @Test
    void 플레이어의_승패_결과를_도출한다() {
        // given
        Dealer dealer = new Dealer(() -> {
            List<Card> cards = new ArrayList<>();

            // 플레이어 3 초기 덱
            cards.add(new Card(Rank.FIVE, Suit.SPADE));
            cards.add(new Card(Rank.FOUR, Suit.HEART));

            // 플레이어 2 초기 덱
            cards.add(new Card(Rank.SEVEN, Suit.SPADE));
            cards.add(new Card(Rank.KING, Suit.HEART));

            // 플레이어 1 초기 덱
            cards.add(new Card(Rank.EIGHT, Suit.HEART));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            // 딜러 덱
            cards.add(new Card(Rank.SEVEN, Suit.CLOVER));
            cards.add(new Card(Rank.KING, Suit.DIAMOND));

            return new Cards(cards);
        });

        Players players = Players.registerPlayers(List.of("pobi", "justin", "neo"), dealer);
        List<Player> playerList = players.getPlayers();

        // when
        Map<Player, WinningResult> winningResults = players.deriveResults(dealer.sumCardNumbers());

        // then
        assertThat(winningResults.get(playerList.getFirst())).isEqualTo(WinningResult.WIN);
        assertThat(winningResults.get(playerList.get(1))).isEqualTo(WinningResult.DRAW);
        assertThat(winningResults.get(playerList.get(2))).isEqualTo(WinningResult.LOSE);
    }
}
