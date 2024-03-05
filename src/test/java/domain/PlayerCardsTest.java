package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerCardsTest {

    @Test
    @DisplayName("처음 지급받는 카드의 수가 두 장이 아니라면 예외가 발생한다.")
    void player_OverSize_ExceptionThrown() {
        assertThatThrownBy(() -> new PlayerCards(List.of(new Card(1), new Card(2), new Card(3))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 카드를 뽑을 수 있다면 참이다.")
    void sum_IsOverMax_True() {
        PlayerCards cards = new PlayerCards(new ArrayList<>(List.of(new Card(10), new Card(11))));

        assertThat(cards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 카드를 뽑을 수 없다면 거짓이다.")
    void sum_IsBelowMax_False() {
        PlayerCards cards = new PlayerCards(new ArrayList<>(List.of(new Card(10), new Card(11))));
        cards.add(new Card(1));

        assertThat(cards.canDraw()).isFalse();
    }
}
