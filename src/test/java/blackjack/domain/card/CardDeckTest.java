package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("카드 덤프에서 카드를 뽑아 반환한다.")
    @Test
    void testDrawRandomCardFromCardDeck() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Card card = cardDeck.drawCard();

        assertThat(card).isNotNull();
    }

    @DisplayName("52장의 카드가 중복이 없다는 것을 확인한다.")
    @Test
    void testCardDeckSize() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();

        Set<Card> cardSet = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            Card card = cardDeck.drawCard();
            cardSet.add(card);
        }
        assertThat(cardSet).hasSize(52);
    }

    @DisplayName("카드 덤프가 빈 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void testCarDeckError() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
        }
        assertThatThrownBy(cardDeck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 카드 덤프가 비어 있습니다!");
    }
}
