package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerCardsTest {

    @Test
    @DisplayName("플레이어가 카드를 뽑을 수 있다면 참이다.")
    void sum_IsOverMax_True() {
        PlayerCards cards = new PlayerCards(new ArrayList<>(List.of(new Card(10), new Card(11))));

        assertThat(cards.canDraw()).isTrue();
    }
}
