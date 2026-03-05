import domain.Player;
import domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlayerAceTest {
    @Test
    @DisplayName("에이스 1장을 가진 상태에서 22점이면 버스트가 아니다")
    void checkBustWithOneAce() throws Exception {
        Player player = new Player("pobi");
        setAceCount(player, 1);
        setScore(player.getScore(), 22);

        boolean busted = player.checkBust();

        assertFalse(busted);
    }

    @Test
    @DisplayName("에이스 여러 장이 있어도 점수 보정 후 음수가 되지 않는다")
    void checkBustWithManyAces() throws Exception {
        Player player = new Player("pobi");
        setAceCount(player, 3);
        setScore(player.getScore(), 31);

        player.checkBust();

        assertEquals(21, player.getScore().getScore());
    }

    private void setAceCount(Player player, int aceCount) throws Exception {
        Field aceCountField = Player.class.getDeclaredField("aceCount");
        aceCountField.setAccessible(true);
        aceCountField.set(player, aceCount);
    }

    private void setScore(Score score, int value) throws Exception {
        Field scoreField = Score.class.getDeclaredField("score");
        scoreField.setAccessible(true);
        scoreField.set(score, value);
    }
}
