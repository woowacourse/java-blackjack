package domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.TrumpSuit;
import blackjack.constant.TrumpRank;
import blackjack.constant.MatchResult;
import blackjack.gambler.PlayerName;
import blackjack.gambler.Player;
import blackjack.gambler.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어의_승패_결과를_도출한다() {
        // given
        Cards cards = makeCards(TrumpRank.ACE, TrumpRank.KING);
        Cards cards2 = makeCards(TrumpRank.SIX, TrumpRank.KING);
        Cards cards3 = makeCards(TrumpRank.EIGHT, TrumpRank.QUEEN);
        Player player1 = new Player(new PlayerName("pobi"),cards);
        Player player2 = new Player(new PlayerName("pobi"),cards2);
        Player player3 = new Player(new PlayerName("pobi"),cards3);
        Players players = new Players(List.of(player1, player2, player3));

        // when
        Map<Player, MatchResult> playerMatchResultMap = players.deriveResults(18);

        // then
        assertThat(playerMatchResultMap.get(player1)).isEqualTo(MatchResult.WIN);
        assertThat(playerMatchResultMap.get(player2)).isEqualTo(MatchResult.LOSE);
        assertThat(playerMatchResultMap.get(player3)).isEqualTo(MatchResult.DRAW);
    }

    private Cards makeCards(TrumpRank rank1, TrumpRank rank2) {
        return new Cards(
                List.of(
                        new Card(rank1, TrumpSuit.HEART),
                        new Card(rank2, TrumpSuit.SPADE)
                ));
    }
}
