package domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Test
    void player는_5명까지_참여_가능하다() {
        //given, when
        List<String> playerNames = List.of("judy", "jude", "pobi", "lio", "neo", "joan");
        List<Player> players = getPlayers(playerNames);

        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레어이는 5명까지 참가 가능합니다.");
    }

    private List<Player> getPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        for (String playerName : playerNames) {
            players.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }
        return players;
    }

    @Test
    void player는_중복된_이름을_가질_수_없다() {
        //given, when
        List<String> playerNames = List.of("judy", "judy");
        List<Player> players = getPlayers(playerNames);

        //then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어 이름은 중복일 수 없습니다.");
    }

    @Test
    void 올바른_입력이_들어왔을_때_잘_생성되는지_확인한다() {
        //given
        List<String> playerNames = List.of("judy", "kevin");
        List<Player> players = getPlayers(playerNames);
        Players playersEntity = new Players(playerNames);

        // when
        String[] expected = {"judy", "kevin"};
        String[] actual = new String[2];

        List<Player> playersPlayers = playersEntity.getPlayers();
        for (int i = 0; i < playersPlayers.size(); i++) {
            Player player = playersPlayers.get(i);
            actual[i] = player.getName();
        }

        //then
        Assertions.assertArrayEquals(expected, actual);
    }
}
