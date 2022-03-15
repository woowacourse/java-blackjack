package blackJack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트")
    void createValidCard() {
        assertThat(Card.from(Suit.SPADE, Denomination.KING)).isNotNull();
    }

    @Test
    @DisplayName("카드 숫자에 따라 점수가 반환되는지 테스트")
    void checkScoreByDenomination() {
        Card card = Card.from(Suit.SPADE, Denomination.KING);
        assertThat(card.getScore()).isEqualTo(10);
    }
}
