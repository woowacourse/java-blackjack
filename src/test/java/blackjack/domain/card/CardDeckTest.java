package blackjack.domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Nested
    @DisplayName("카드 덱 초기화 테스트")
    class InitCardDeckTest {

        @Test
        @DisplayName("카드덱을 생성할 수 있다.")
        void checkSuitCount() {
            CardDeck cardDeck = CardDeck.createCardDeck();

            assertThat(cardDeck).isInstanceOf(CardDeck.class);
        }
    }

    @Nested
    @DisplayName("카드 뽑기 테스트")
    class PickCardTest {

        @Test
        @DisplayName("랜덤으로 1장의 카드를 뽑을 수 있다.")
        void pickOneCard() {
            CardDeck cardDeck = CardDeck.createCardDeck();
            Card pickedCard = cardDeck.pickRandomCard();

            assertThat(pickedCard).isInstanceOf(Card.class);
        }
    }
}
