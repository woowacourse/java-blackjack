package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("최대 점수 조건을 넘지 않을 경우 카드를 뽑는다.")
    void draw_IsBelowMaxScore_SizeUp() {
        Player player = new Player(new Name("capy"));
        PlayerCards cards = new PlayerCards(player, List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB)));

        assertThat(cards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("최대 점수 조건을 넘을 경우 카드를 뽑지 않는다.")
    void draw_IsOverMaxScore_SizeMaintain() {
        Player player = new Player(new Name("capy"));
        PlayerCards cards = new PlayerCards(player, List.of(new Card(11, Shape.CLUB), new Card(11, Shape.CLUB)));


        assertThat(cards.canDraw()).isFalse();
    }
}
