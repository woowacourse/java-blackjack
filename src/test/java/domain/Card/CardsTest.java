package domain.Card;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    @DisplayName("블랙잭 게임 카드는 52장까지 뽑을 수 있다.")
    void drawCardTest() {
        //given
        Cards cards = new Cards();

        //when
        IntStream.range(0,52).forEach(index -> cards.drawCard());

        //then
        assertThrows(IllegalArgumentException.class, cards::drawCard);
    }
}
