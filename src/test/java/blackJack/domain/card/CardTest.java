package blackJack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성을 성공적으로 할 경우 null 값을 반환하지 않는다.")
    void createValidCard() {
        assertThat(Card.valueOf(Suit.SPADE, Denomination.KING)).isNotNull();
    }

    @Test
    @DisplayName("카드 숫자에 따라 카드의 점수를 반환한다.")
    void checkScoreByDenomination() {
        Card card = Card.valueOf(Suit.SPADE, Denomination.KING);
        assertThat(card.getScore()).isEqualTo(10);
    }
}
