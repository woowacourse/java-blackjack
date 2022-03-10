package BlackJack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static BlackJack.domain.CardFactory.CARD_CACHE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    @Test
    @DisplayName("두장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawTwoCardsTest() {
        Cards actual = CardFactory.drawTwoCards();
        int expectedDrawnCards = 2;
        int expectedCardCache = 50;
        assertThat(actual.getDeck().size()).isEqualTo(expectedDrawnCards);
        assertThat(CARD_CACHE.size()).isEqualTo(expectedCardCache);
    }

    @Test
    @DisplayName("한장의 카드를 나눠주고 나눠준 카드는 카드 캐쉬에서 지운다.")
    void drawOneCard() {
        Card actual = CardFactory.drawOneCard();
        assertThat(CARD_CACHE.contains(actual)).isEqualTo(false);
    }
}