package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;
import domain.constant.WinDrawLose;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어의_승패_결과를_도출한다() {
        // given
        Cards cards = new Cards(
                List.of(new Card(TrumpNumber.KING, TrumpEmblem.HEART),
                        new Card(TrumpNumber.ACE, TrumpEmblem.SPADE)
                ));
        Cards cards2 = new Cards(
                List.of(new Card(TrumpNumber.KING, TrumpEmblem.HEART),
                        new Card(TrumpNumber.SIX, TrumpEmblem.SPADE)
                ));
        Cards cards3 = new Cards(
                List.of(new Card(TrumpNumber.KING, TrumpEmblem.HEART),
                        new Card(TrumpNumber.EIGHT, TrumpEmblem.SPADE)
                ));
        Player player1 = new Player(new Nickname("pobi"),cards); // 21 승
        Player player2 = new Player(new Nickname("pobi"),cards2); // 16 패
        Player player3 = new Player(new Nickname("pobi"),cards3); // 18 무
        Players players = new Players(List.of(player1, player2, player3));

        // when
        Map<Player, WinDrawLose> playerWinDrawLoseMap = players.deriveResults(18);

        // then
        assertThat(playerWinDrawLoseMap.get(player1)).isEqualTo(WinDrawLose.WIN);
        assertThat(playerWinDrawLoseMap.get(player2)).isEqualTo(WinDrawLose.LOSE);
        assertThat(playerWinDrawLoseMap.get(player3)).isEqualTo(WinDrawLose.DRAW);
    }
}
