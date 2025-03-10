package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import domain.constant.MatchResult;
import domain.gambler.Nickname;
import domain.gambler.Player;
import domain.gambler.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어의_승패_결과를_도출한다() {
        // given
        Cards cards = makeCards(TrumpNumber.ACE, TrumpNumber.KING);
        Cards cards2 = makeCards(TrumpNumber.SIX, TrumpNumber.KING);
        Cards cards3 = makeCards(TrumpNumber.EIGHT, TrumpNumber.QUEEN);
        Player player1 = new Player(new Nickname("pobi"),cards);
        Player player2 = new Player(new Nickname("pobi"),cards2);
        Player player3 = new Player(new Nickname("pobi"),cards3);
        Players players = new Players(List.of(player1, player2, player3));

        // when
        Map<Player, MatchResult> playerMatchResultMap = players.deriveResults(18);

        // then
        assertThat(playerMatchResultMap.get(player1)).isEqualTo(MatchResult.WIN);
        assertThat(playerMatchResultMap.get(player2)).isEqualTo(MatchResult.LOSE);
        assertThat(playerMatchResultMap.get(player3)).isEqualTo(MatchResult.DRAW);
    }

    private Cards makeCards(TrumpNumber number1, TrumpNumber number2) {
        return new Cards(
                List.of(
                        new Card(number1, TrumpEmblem.HEART),
                        new Card(number2, TrumpEmblem.SPADE)
                ));
    }
}
