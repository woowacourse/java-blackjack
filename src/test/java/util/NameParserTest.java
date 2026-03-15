package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Players;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameParserTest {
    @Test
    @DisplayName("이름 문자열을 쉼표로 분리해 플레이어 수를 만든다")
    void makePlayersSize() {
        Players players = NameParser.makeNameList("pobi,jason");

        assertEquals(2, players.getSize());
    }

    @Test
    @DisplayName("쉼표 뒤 공백을 제거해 이름을 저장한다")
    void trimPlayerName() {
        Players players = NameParser.makeNameList("pobi, jason");

        final List<String> playerNames = new ArrayList<>();
        players.forEachPlayer(player -> playerNames.add(player.getName()));
        assertEquals("jason", playerNames.get(1));
    }
}
