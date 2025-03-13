package participant;

import game.Card;
import game.Cards;
import game.Deck;
import constant.TrumpEmblem;
import constant.TrumpNumber;
import constant.WinningResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    void 플레이어의_승패_결과를_도출한다() {
        // given
        Players players = Players.registerPlayers(
                List.of("pobi", "justin", "neo"),
                new Deck(() -> {
                    List<Card> cards = new ArrayList<>();

                    // 플레이어 3 초기 덱
                    cards.add(new Card(TrumpNumber.FIVE, TrumpEmblem.SPADE));
                    cards.add(new Card(TrumpNumber.FOUR, TrumpEmblem.HEART));

                    // 플레이어 2 초기 덱
                    cards.add(new Card(TrumpNumber.SEVEN, TrumpEmblem.SPADE));
                    cards.add(new Card(TrumpNumber.KING, TrumpEmblem.HEART));

                    // 플레이어 1 초기 덱
                    cards.add(new Card(TrumpNumber.EIGHT, TrumpEmblem.HEART));
                    cards.add(new Card(TrumpNumber.KING, TrumpEmblem.DIAMOND));

                    return new Cards(cards);
                }));

        List<Player> playerList = players.getPlayers();

        // when
        Map<Player, WinningResult> winningResults = players.deriveResults(17);


        // then
        assertThat(winningResults.get(playerList.getFirst())).isEqualTo(WinningResult.WIN);
        assertThat(winningResults.get(playerList.get(1))).isEqualTo(WinningResult.DRAW);
        assertThat(winningResults.get(playerList.get(2))).isEqualTo(WinningResult.LOSE);
    }
}
