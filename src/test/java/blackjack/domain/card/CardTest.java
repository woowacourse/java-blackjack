package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드 객체 생성")
    @Test
    void create() {
        assertThatCode(() -> Card.of(Suit.CLUB, Denomination.KING))
                .doesNotThrowAnyException();
    }

    @DisplayName("캐싱한 카드 52장 제공")
    @Test
    void createDeck() {
        assertThat(Card.createDeck().size()).isEqualTo(52);
    }
}
