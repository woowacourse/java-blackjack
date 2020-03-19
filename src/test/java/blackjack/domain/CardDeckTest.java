package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @DisplayName("52장의 카드 한 벌을 생성하는지 확인")
    @Test
    void cardDeckSizeTest() {
        int expected = 52;

        CardDeck cardDeck = CardDeckFactory.create();
        assertThat(cardDeck.getSize()).isEqualTo(expected);
    }
}
