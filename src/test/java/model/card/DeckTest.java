package model.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("Deck에서 카드를 한 장 뽑는다.")
    void makeDeck() {
        //given
        Deck deck = Deck.of();
        //when
        Card card = deck.pick();

        //then
        assertThat(card).isNotNull();
    }

    @Test
    @DisplayName("Deck에서 모든 카드를 소진한 후, 추가로 뽑으면 예외 발생")
    void throwExceptionWhenDeckIsEmpty() {
        // given
        Deck deck = Deck.of();

        // when
        while (true) {
            try {
                deck.pick();
            } catch (IllegalStateException e) {
                // then
                assertThat(e).isInstanceOf(IllegalStateException.class)
                        .hasMessage("[ERROR] 주어진 모든 카드들을 소진하였습니다");
                break;
            }
        }
    }
}
