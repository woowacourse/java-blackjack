package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어를 생성한다.")
    void createPlayerTest() {
        Assertions.assertDoesNotThrow(() -> new Player("pobi"));
    }

    @Test
    @DisplayName("카드를 받는다.")
    void receiveCard() {
        Player player = new Player("pobi");
        Card card = new Card(CardNumber.ACE,CardPattern.SPADE);
        player.addCard(card);
        assertThat(player.getCard(0)).isEqualTo(card);
    }
}
