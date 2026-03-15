package domain.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    @DisplayName("Players는 생성 시 전달된 플레이어 수를 보관한다")
    void playersSize() {
        Players players = new Players(List.of(new Player("pobi"), new Player("jason")));

        assertEquals(2, players.getSize());
    }

    @Test
    @DisplayName("forEachPlayer로 플레이어 이름 순회를 할 수 있다")
    void forEachPlayer() {
        Players players = new Players(List.of(new Player("pobi"), new Player("jason")));
        List<String> names = new ArrayList<>();

        players.forEachPlayer(player -> names.add(player.getName()));

        assertEquals(List.of("pobi", "jason"), names);
    }
}
