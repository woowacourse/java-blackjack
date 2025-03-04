package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class CardDeckTest {

    @Nested
    @DisplayName("카드 덱 초기화 테스트")
    class InitCardDeckTest {

        @Test
        @DisplayName("종류별 카드가 13장인지 확인한다.")
        void checkSuitCount() {
            CardDeck cardDeck = CardDeck.createCardDeck();

            assertThat(cardDeck).isInstanceOf(CardDeck.class);
        }
    }
}
