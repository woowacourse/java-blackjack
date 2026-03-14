package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {
    private static final CardShuffleStrategy FIXED_CARD_SHUFFLE_STRATEGY = cards -> {
    };

    @Test
    @DisplayName("전체 덱 생성 시 52장의 카드가 들어있는 카드 덱이 생성된다.")
    void shouldReturnTotalDeckWithAllCards() {
        // when
        Deck totalDeck = Deck.createTotalDeckAndShuffle(FIXED_CARD_SHUFFLE_STRATEGY);
        for (int i = 0; i < 52; i++) {
            totalDeck.drawCard();
        }

        // then
        assertThatThrownBy(totalDeck::drawCard)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Deck에서 카드를 한장 뽑으면 해당 카드를 리턴하고, Deck에서 카드가 한장 제거된다.")
    void shouldReturnSingleCardAndRemoveCardFromDeck() {
        // given
        Deck totalDeck = Deck.createTotalDeckAndShuffle(FIXED_CARD_SHUFFLE_STRATEGY);
        Card expected = new Card(CardShape.SPADE, CardContents.A);

        // when
        Card result = totalDeck.drawCard();
        for (int i = 0; i < 51; i++) {
            totalDeck.drawCard();
        }

        // then
        assertThat(result).isEqualTo(expected);
        assertThatThrownBy(totalDeck::drawCard)
                .isInstanceOf(NoSuchElementException.class);
    }
}
