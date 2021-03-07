package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardDeckTest {

    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new Cards(Card.values()));
    }

    @Test
    @DisplayName("카드 드로우 성공")
    void drawCardDeck() {
        assertThat(cardDeck.drawCard()).isEqualTo(new Card(Shape.SPADE, Denomination.ACE));
    }

    @Test
    @DisplayName("남아 있는 카드가 없어 드로우 실패")
    void noRemainCard() {
        for (int i = 0; i < 52; i++) {
            cardDeck.drawCard();
        }
        assertThatThrownBy(() -> cardDeck.drawCard()).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
