package domain.card;

import domain.Name;
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
        assertThatThrownBy(() -> new PlayerCards(new Name("capy"), List.of(new Card(1, Shape.CLUB), new Card(2, Shape.CLUB), new Card(3, Shape.CLUB))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플러이어의 카드 숫자의 합이 최소 점수 조건 이하면 뽑을 수 있다.")
    void sum_IsOverMax_True() {
        PlayerCards cards = new PlayerCards(new Name("capy"), new ArrayList<>(List.of(new Card(10, Shape.CLUB), new Card(11, Shape.CLUB))));

        assertThat(cards.canDraw()).isTrue();
    }

    @Test
    @DisplayName("플레이어의 카드 숫자의 합이 최대 점수 조건 초과면 뽑을 수 없다.")
    void sum_IsBelowMax_False() {
        PlayerCards cards = new PlayerCards(new Name("capy"), new ArrayList<>(List.of(new Card(10, Shape.CLUB), new Card(10, Shape.CLUB))));
        cards.receive(new Card(2, Shape.CLUB));

        assertThat(cards.canDraw()).isFalse();
    }

    @Test
    @DisplayName("딜러가 카드를 뽑는다.")
    void draw_SizeUp() {
        PlayerCards playerCards = new PlayerCards(new Name("capy"), new ArrayList<>(List.of(new Card(6, Shape.CLUB), new Card(10, Shape.CLUB))));

        playerCards.receive(new Card(1, Shape.CLUB));

        assertThat(playerCards.cards).hasSize(3);
    }

    @Test
    @DisplayName("현재 뽑은 카드의 가장 좋은 점수의 합을 반환한다.")
    void bestSum() {
        PlayerCards playerCards = new PlayerCards(new Name("capy"), new ArrayList<>(List.of(new Card(1, Shape.CLUB), new Card(1, Shape.CLUB))));

        assertThat(playerCards.bestSum()).isEqualTo(12);
    }
}
