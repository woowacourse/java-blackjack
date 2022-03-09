package blackjack;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("카드 받는 기능 테스트")
    @Test
    void addCard() {
        Player player = new Player();
        player.addCard(new Card("3다이아몬드", 3));
        int playerCardSize = player.getCards().size();
        assertThat(playerCardSize).isEqualTo(1);
    }
}
