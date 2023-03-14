package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.exception.NoMoreCardException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 52개 생성한다")
    void createCardDeckTest() {
        CardDeck cardDeck = new CardDeck();

        assertThat(cardDeck.getCards()).hasSize(52);
    }

    @Test
    @DisplayName("카드 한 장을 나눠준다")
    void pickCardTest() {
        CardDeck cardDeck = new CardDeck();

        cardDeck.pick();

        assertThat(cardDeck.getCards()).hasSize(51);
    }

    @Test
    @DisplayName("카드를 뽑을 때 카드 덱이 비었다면 예외를 발생한다")
    void pickCardEmptyTest() {
        CardDeck cardDeck = new CardDeck(List.of());

        assertThatThrownBy(() -> cardDeck.pick())
                .isInstanceOf(NoMoreCardException.class);
    }
}
