package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("가진 카드 중 ACE가 몇개인지 return한다.")
    @Test
    void countAceCard() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(cards.countAceCard()).isEqualTo(2);
    }
}