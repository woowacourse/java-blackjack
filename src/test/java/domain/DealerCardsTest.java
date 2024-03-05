package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DealerCardsTest {

    @Test
    @DisplayName("처음 지급받는 카드의 수가 두 장이 아니라면 예외가 발생한다.")
    void player_OverSize_ExceptionThrown() {
        assertThatThrownBy(() -> new DealerCards(List.of(new Card(1), new Card(2), new Card(3))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하라면 참이다.")
    void canDraw_isBelowMinScore_True() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>(List.of(new Card(6), new Card(10))));

        assertThat(dealerCards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하라면 참이다.")
    void canDraw_isOverMinScore_False() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>(List.of(new Card(6), new Card(11))));

        assertThat(dealerCards.canDraw()).isFalse();
    }
}
