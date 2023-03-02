package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.card.exception.NoMoreCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 52장이 생성되는지 테스트")
    void pick_random_card() {
        CardDeck cardDeck = CardDeck.create();
        for (int i = 0; i < 52; i++) {
            cardDeck.pick();
        }
        assertThrows(NoMoreCardException.class, () -> cardDeck.pick());
    }

    @Test
    @DisplayName("카드 뽑기 테스트")
    void pick_test() {
        CardDeck cardDeck = CardDeck.create();
        Card spadeAce = cardDeck.pick();
        Card spadeTwo = cardDeck.pick();

        assertThat(spadeAce.getShape()).isEqualTo(Shape.SPADE);
        assertThat(spadeAce.getNumber()).isEqualTo(Number.ACE);

        assertThat(spadeTwo.getShape()).isEqualTo(Shape.SPADE);
        assertThat(spadeTwo.getNumber()).isEqualTo(Number.TWO);
    }
}
