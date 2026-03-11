package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 초과하면 isBurst는 true를 반환한다.")
    void isBurst_Score22_ReturnTrue() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.TEN));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        assertEquals(true, player.isBurst());
    }

    @Test
    @DisplayName("플레이어의 점수가 21점이면 isBurst는 false를 반환한다.")
    void isBurst_Score21_ReturnFalse() {
        Player player = new Player(new Name("pobi"));
        player.receiveCard(new Card(Shape.SPADE, Number.NINE));
        player.receiveCard(new Card(Shape.HEART, Number.JACK));
        player.receiveCard(new Card(Shape.DIAMOND, Number.TWO));

        assertEquals(false, player.isBurst());
    }

    @Test
    @DisplayName("")
    void isWin() {
    }
}
