package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    @Test
    @DisplayName("카드 덱은 초기화하면 52장이다.")
    void whenInitReturn52Size() {
        // given
        // when
        CardDeck cardDeck = CardDeck.initialize();

        // then
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("요청된 개수만큼 카드를 제공한다.")
    void provideCardsAsRequested() {
        //given
        CardDeck cardDeck = CardDeck.initialize();
        int expectedCardCount = 2;

        //when
        List<Card> drawnCards = cardDeck.drawCard(expectedCardCount);

        //then
        assertThat(drawnCards).hasSize(expectedCardCount);
    }


}