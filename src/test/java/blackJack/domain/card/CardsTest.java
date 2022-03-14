package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 더해주는 테스트")
    void addCards()  {
        final Cards cards = new Cards();

        assertDoesNotThrow(() -> cards.addCard(new Card(Symbol.SPADE, Denomination.EIGHT)));
    }

    @Test
    @DisplayName("중복된 카드가 분배되는 경우 에러 테스트")
    void addInvalidDistributeCard() {
        final Cards cards = new Cards();
        cards.addCard(new Card(Symbol.SPADE, Denomination.EIGHT));

        assertThatThrownBy(() -> cards.addCard(new Card(Symbol.SPADE, Denomination.EIGHT)));
    }

    @Test
    @DisplayName("분배된 카드들의 총 합을 구해주는 기능 테스트")
    void totalCardCalculateScore() {
        final Cards cards = new Cards();
        cards.addCard(new Card(Symbol.SPADE, Denomination.EIGHT));
        cards.addCard(new Card(Symbol.SPADE, Denomination.NINE));

        assertThat(cards.addScore()).isEqualTo(17);
    }
}
