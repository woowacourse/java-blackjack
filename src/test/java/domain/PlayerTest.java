package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 이름은 1글자 이상 8글자 이하여야 한다.")
    void 이름은_1글자_이상_8글자_이하_성공() {
        Assertions.assertDoesNotThrow(() -> new Player("pobi"));
    }

    @Test
    @DisplayName("플레이어 이름은 공백을 허용하지 않는다.")
    void 이름_공백_실패() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(""));
    }

    @Test
    @DisplayName("플레이어 이름은 8글자 초과를 허용하지 않는다.")
    void 이름_8글자_초과_실패() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("pobipobip"));
    }

    @Test
    @DisplayName("플레이어 이름은 특수 문자를 허용하지 않는다.")
    void 이름_특수_문자_실패() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("&*&@$"));
    }

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        Player player = new Player("pobi");
        player.hit(new Card(Rank.ACE, Suit.DIAMOND));

        Assertions.assertEquals(player.getHand().getHand().size(), 1);
    }
}
