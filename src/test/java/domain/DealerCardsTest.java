package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerCardsTest {

    @Test
    @DisplayName("딜러의 카드 숫자의 합이 최소 점수 조건 이하라면 참이다.")
    void canDraw_isBelowMinScore_True() {
        DealerCards dealerCards = new DealerCards(new ArrayList<>(List.of(new Card(6), new Card(8))));
        
        assertThat(dealerCards.canDraw()).isTrue();
    }
}