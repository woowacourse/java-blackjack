package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerTest {

    @Test
    @DisplayName("새로운 카드를 추가한다.")
    void pickNewCard() {
        Player player = new Player(new Name("hello"), List.of(new Card(Shape.DIAMOND, Value.TWO), new Card(Shape.CLOVER, Value.ACE)));

        player.pick(new Card(Shape.DIAMOND, Value.JACK));

        assertThat(player.getAllCards().size()).isEqualTo(3);
    }

}
