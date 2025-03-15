package domain.card;

import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 카드덱에_카드가_존재하지_않을_때_드로우하면_예외를_던진다() {
        //given
        CardDeck cardDeck = CardDeck.createCards();
        IntStream.rangeClosed(1, 52)
                .forEach(count -> cardDeck.drawCard());

        //when & then
        Assertions.assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
