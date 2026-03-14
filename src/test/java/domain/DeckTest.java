package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import common.ErrorMessage;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {
    Deck deck;
    CardCreationStrategy fixedCardCreationStrategy = () -> {
        Card spadeJ = new Card(CardShape.스페이드, CardContents.J);
        Card clover5 = new Card(CardShape.클로버, CardContents.FIVE);

        return new ArrayDeque<>(List.of(spadeJ, clover5));
    };

    @BeforeEach
    void init() {
        deck = Deck.createDeck(fixedCardCreationStrategy); //TODO : 전략 넣기
    }

    @Test
    @DisplayName("Deck를 생성할 때 오류 발생 안함")
    void deck_create_success() {
        assertDoesNotThrow(
                () -> Deck.createDeck(fixedCardCreationStrategy)
        );
    }

    @Nested
    class drawTest {
        @Test
        @DisplayName("Deck에서 카드를 한장 뽑아줌")
        void draw_card_success() {
            Card result = deck.drawCard();
            Card expected = new Card(CardShape.스페이드, CardContents.J);
            assertThat(result).isEqualTo(expected);
        }

        @Test
        @DisplayName("Deck에서 카드를 두장 뽑아줌")
        void draw_two_cards_success() {
            List<Card> result = deck.drawTwoCards();
            Card expectedCard1 = new Card(CardShape.스페이드, CardContents.J);
            Card expectedCard2 = new Card(CardShape.클로버, CardContents.FIVE);

            assertTrue(result.contains(expectedCard1));
            assertTrue(result.contains(expectedCard2));
        }

        @Test
        @DisplayName("Deck에서 0이하 혹은 남은 카드 이상의 숫자 선택 시도 시 오류 발생")
        void draw_card_fail() {
            deck.drawTwoCards();

            assertThatThrownBy(
                    () -> deck.drawCard()
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.DRAW_CARD_OUT_OF_RANGE.getMessage());
        }
    }
}