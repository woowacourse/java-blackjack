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
        assertThatThrownBy(() -> new DealerCards(new Dealer(), List.of(new Card(1, Shape.CLUB), new Card(2, Shape.CLUB), new Card(3, Shape.CLUB))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하면 뽑을 수 있다.")
    void canDraw_isBelowMinScore_True() {
        DealerCards dealerCards = new DealerCards(new Dealer(), new ArrayList<>(List.of(new Card(6, Shape.CLUB), new Card(10, Shape.CLUB))));

        assertThat(dealerCards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 초과면 뽑을 수 없다.")
    void canDraw_isOverMinScore_False() {
        DealerCards dealerCards = new DealerCards(new Dealer(), new ArrayList<>(List.of(new Card(6, Shape.CLUB), new Card(11, Shape.CLUB))));

        assertThat(dealerCards.canDraw()).isFalse();
    }
}
