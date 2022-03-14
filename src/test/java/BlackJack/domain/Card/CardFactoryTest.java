package BlackJack.domain.Card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardFactoryTest {

    @Test
    @DisplayName("두장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawTwoCardsTest() {
        CardFactory cardFactory = new CardFactory();
        Cards actual = cardFactory.initCards();
        int expectedDrawnCards = 2;
        int expectedCardCache = 50;
        assertThat(actual.getCards().size()).isEqualTo(expectedDrawnCards);
        assertThat(cardFactory.size()).isEqualTo(expectedCardCache);
    }

    @Test
    @DisplayName("한장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawOneCard() {
        CardFactory cardFactory = new CardFactory();
        cardFactory.drawOneCard();
        int expectedCardCache = 51;
        assertThat(cardFactory.size()).isEqualTo(expectedCardCache);
    }
}
