package domain;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayersTest {

    @Test
    void 올바른_입력이_들어왔을_때_잘_생성되는지_확인한다() {
        //given
        List<String> playerNames = List.of("judy", "kevin");
        Players playersEntity = new Players(createPlayers(playerNames));

        // when
        String[] expected = {"judy", "kevin"};
        String[] actual = new String[2];

        List<Player> playersPlayers = playersEntity.getPlayers();
        for (int i = 0; i < playersPlayers.size(); i++) {
            Player player = playersPlayers.get(i);
            actual[i] = player.getName();
        }

        //then
        assertArrayEquals(expected, actual);
    }

    private List<Player> createPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }

        return players;
    }
}
