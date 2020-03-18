package domain;

import domain.card.Card;
import domain.card.CardDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    private CardDeck cardDeck;

    @BeforeEach
    private void setUp() {
        cardDeck = new CardDeck();
    }

    @DisplayName("CardDeck 객체 생성 테스트")
    @Test
    void cardsConstructTest() {
        Assertions.assertThat(cardDeck).isInstanceOf(CardDeck.class);
    }

    @DisplayName("pop 기능 테스트")
    @Test
    void popTest() {
        Card popCard = cardDeck.giveCard();

        Assertions.assertThat(popCard).isInstanceOf(Card.class);
    }

    @DisplayName("카드 덱을 모두 소모했을 때 예외 처리 테스트")
    @Test
    void popWhenEmptyDeckTest() {
        Assertions.assertThatThrownBy(() -> {
            for (int i = 0; i < 49; i++) {
                Card card = cardDeck.giveCard();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void giveTwoCardStartGameTest() {
        CardDeck cardDeck = new CardDeck();

        Assertions.assertThat(cardDeck.giveTwoCardStartGame().size()).isEqualTo(2);
    }
}
