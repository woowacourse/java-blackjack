package blackJack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드를 성공적으로 생성할 경우 예외가 발생하지 않는다.")
    void addCards()  {
        final Cards cards = new Cards();

        assertDoesNotThrow(() -> cards.addCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT)));
    }

    @Test
    @DisplayName("중복된 카드를 분배할 경우 예외가 발생한다.")
    void addInvalidDistributeCard() {
        final Cards cards = new Cards();
        cards.addCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT));

        assertThatThrownBy(() -> cards.addCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT)));
    }

    @Test
    @DisplayName("분배된 카드들의 총 합을 구할 수 있다.")
    void totalCardCalculateScore() {
        final Cards cards = new Cards();
        cards.addCard(Card.valueOf(Suit.SPADE, Denomination.EIGHT));
        cards.addCard(Card.valueOf(Suit.SPADE, Denomination.NINE));

        assertThat(cards.addScore()).isEqualTo(17);
    }
}
