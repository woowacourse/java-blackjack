package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("플레이어가 처음 카드를 뽑으면 패의 크기는 1이다.")
    @Test
    void drawTest() {
        Player player = new Player();

        Assertions.assertEquals(0, player.getCards().size());
        player.drawCard();
        Assertions.assertEquals(1, player.getCards().size());
    }
}
