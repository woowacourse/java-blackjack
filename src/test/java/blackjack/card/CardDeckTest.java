package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Nested
    @DisplayName("카드 덱 초기화 테스트")
    class InitCardDeckTest {

        @Test
        @DisplayName("카드덱을 생성할 수 있다.")
        void checkSuitCount() {
            CardDeck cardDeck = CardDeck.shuffleCardDeck();

            assertThat(cardDeck).isInstanceOf(CardDeck.class);
        }
    }

    @Nested
    @DisplayName("카드 뽑기 테스트")
    class PickCardTest {

        @Test
        @DisplayName("랜덤으로 1장의 카드를 뽑을 수 있다.")
        void pickOneCard() {
            CardDeck cardDeck = CardDeck.shuffleCardDeck();
            Card pickedCard = cardDeck.pickRandomCard();

            assertThat(pickedCard).isInstanceOf(Card.class);
        }

        @Test
        @DisplayName("1장의 카드를 뽑으면 카드덱에서 제거된다.")
        void removeOneCard() {
            CardDeck cardDeck = CardDeck.shuffleCardDeck();
            Card pickedCard = cardDeck.pickRandomCard();

            assertThat(cardDeck.getCards()).doesNotContain(pickedCard);
        }

        @Test
        @DisplayName("초기 카드에서 1장의 카드를 뽑으면 51장의 카드가 남는다.")
        void remainCardsOf51() {
            CardDeck cardDeck = CardDeck.shuffleCardDeck();
            cardDeck.pickRandomCard();

            assertThat(cardDeck.getCards()).hasSize(51);
        }

        @Test
        @DisplayName("카드덱에 카드가 존재하지 않으면 예외가 발생한다.")
        void pickFromEmptyDeck() {
            CardDeck cardDeck = CardDeck.shuffleCardDeck();
            for (int i = 0; i < 52; i++) {
                cardDeck.pickRandomCard();
            }

            assertThatThrownBy(cardDeck::pickRandomCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드덱의 카드를 모두 소진하여 더이상 카드를 뽑을 수 없습니다.");
        }
    }
}
