package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardsTest {

    @Test
    @DisplayName("처음 지급받는 카드의 수가 두 장이 아니라면 예외가 발생한다.")
    void player_OverSize_ExceptionThrown() {
        assertThatThrownBy(() -> new Cards(List.of(new Card(1), new Card(2), new Card(3))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 숫자 합이 최대 점수 조건을 초과한다면 참이다.")
    void sum_IsOverMax_True() {
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(6), new Card(11))));
        cards.add(new Card(7));

        assertThat(cards.isOverMaxScore()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하라면 참이다.")
    void sum_IsBelowMin_True() {
        Cards cards = new Cards(new ArrayList<>(List.of(new Card(6), new Card(8))));

        assertThat(cards.isBelowMinScore()).isTrue();
    }
}
