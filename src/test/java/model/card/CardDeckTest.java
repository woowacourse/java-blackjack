package model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @DisplayName("덱이 비어있을 때 카드를 뽑으면 예외가 발생한다.")
    @Test
    void validateNotEmpty() {
        CardDeck cardDeck = new CardDeck(List.of());

        assertThatThrownBy(cardDeck::selectRandomCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 뽑을 카드가 없습니다.");

    }
}
