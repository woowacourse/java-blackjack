import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest(){
        Player player = new Player(new PlayerName("pobi"));

        player.receiveCard(new Card(Shape.SPADE, Number.A));

        Assertions.assertThat(player).extracting("cards")
                .asList()
                .hasSize(1);
    }
}
