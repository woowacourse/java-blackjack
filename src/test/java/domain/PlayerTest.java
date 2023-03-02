package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private List<Card> cards = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cards.add(new Card(Shape.DIAMOND, Value.TWO));
        cards.add(new Card(Shape.CLOVER, Value.ACE));
    }

    @Test
    @DisplayName("새로운 카드를 추가한다.")
    void pickNewCard() {
        Player player = new Player(new Name("hello"), new Cards(cards));

        player.pick(new Card(Shape.DIAMOND, Value.JACK));

        assertThat(player.getCards().getCards().size()).isEqualTo(3);
    }

}
