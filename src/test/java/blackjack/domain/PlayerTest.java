package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    @DisplayName("이름에 공백 입력 경우 예외 처리")
    void playerNameSplitException() {
        String input = "pobi, jason";
        assertThatThrownBy(() -> {
            for (String name : input.split(",")) {
                new Player(name);
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkReceiveCard() {
        Card card = new Card(CardPattern.CLOVER, CardNumber.TEN);
        Player player = new Player("pobi");
        player.receiveCard(card);
        assertEquals(player.calculatePoint(), 10);
    }
}
