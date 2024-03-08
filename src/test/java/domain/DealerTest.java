package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("최소 점수 조건을 넘지 않을 경우 카드를 뽑는다.")
    void draw_IsBelowMinScore_SizeUp() {
        DealerCards cards = new DealerCards(List.of(new Card(10, Shape.CLUB), new Card(6, Shape.CLUB)));

        assertThat(cards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("최소 점수 조건을 넘는 경우 카드를 뽑지 않는다.")
    void draw_IsBelowMinScore_SizeMaintain() {
        DealerCards cards = new DealerCards(List.of(new Card(10, Shape.CLUB), new Card(7, Shape.CLUB)));

        assertThat(cards.canDraw()).isFalse();
    }
}
