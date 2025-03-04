import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드를 추가할 수 있다")
    @Test
    void test1() {
        //given
        Cards cards = Cards.empty();
        Card card = new Card(CardNumber.TWO, CardShape.CLOVER);
        //when
        cards.add(card);
        //then
        assertThat(cards.getCards()).contains(card);
    }
}
