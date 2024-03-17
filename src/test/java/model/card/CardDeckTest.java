package model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardDeckTest {

    @DisplayName("52개의 카드를 다 뽑아서, 덱이 비어있을 때 카드를 뽑으면 예외가 발생한다.")
    @Test
    void validateNotEmpty() {
        CardDeck cardDeck = new CardDeck(Card.createCardDeck());
        IntStream.range(0, 52)
                .forEach(i -> cardDeck.selectRandomCard());

        assertThatThrownBy(cardDeck::selectRandomCard)
                .isInstanceOf(IllegalStateException.class);

    }
}
