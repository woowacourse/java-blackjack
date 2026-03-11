package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import exception.GameException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestPlayers {

    @Test
    public void 정상_작동() {
        Player player = new Player(new PlayerName("player1"), new BattingMoney("10000"));
        Player player2 = new Player(new PlayerName("player2"), new BattingMoney("10000"));

        Players players = new Players(List.of(player,player2));

        List<Player> playerList = players.getPlayers();

        assertThat(playerList.size()).isEqualTo(2);
        assertThat(playerList.getFirst()).isEqualTo(player);
        assertThat(playerList.getLast()).isEqualTo(player2);
    }

    @Test
    public void 중복_이름_예외() {
        PlayerName playerName = new PlayerName("player");
        PlayerName playerName2 = new PlayerName("player");
        BattingMoney battingMoney = new BattingMoney("10000");

        Player player = new Player(playerName, battingMoney);
        Player player2 = new Player(playerName2, battingMoney);

        assertThatThrownBy(() -> new Players(List.of(player, player2)))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("중복된 이름이 있습니다.");
    }
}
