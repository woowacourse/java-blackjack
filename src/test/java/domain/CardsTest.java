package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("Cards에 Card를 추가한다.")
    void add() {
        Cards cards = new Cards();

        cards.add(new Card(CardNumber.ACE,CardPattern.SPADE));

        Assertions.assertThat(cards.getCard(0))
                .usingRecursiveComparison()
                .isEqualTo(new Card(CardNumber.ACE,CardPattern.SPADE));
    }



}
