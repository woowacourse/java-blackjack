package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDequeTest {

    @Test
    @DisplayName("덱에서 카드 한 장을 가져올 수 있어야 한다.")
    void should_create_cardDeque() {
        // given
        Card heartOne = new Card(Shape.HEART, Rank.ONE);
        CardDeque cardDeque = new CardDeque(List.of(heartOne));

        // when
        Card result = cardDeque.getAndRemoveFrontCard();

        // when
        assertThat(result).isEqualTo(heartOne);
    }

    @Test
    @DisplayName("덱에서 카드를 가져올 때, 기존 카드의 크기가 한 장 줄어들어야 한다.")
    void when_get_card_from_deque_then_cards_size_1_minus() {
        // given
        Card heartOne = new Card(Shape.HEART, Rank.ONE);
        CardDeque cardDeque = new CardDeque(List.of(heartOne));

        // when
        cardDeque.getAndRemoveFrontCard();

        // then
        Deque<Card> deque = cardDeque.getDeque();
        assertThat(deque.size()).isEqualTo(0);
    }
}
