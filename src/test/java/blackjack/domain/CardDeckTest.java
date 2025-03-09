package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 덱은 초기화하면 52장이다.")
    void whenInitReturn52Size() {
        // given
        // when
        CardDeck cardDeck = new CardDeck();

        // then
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("요청된 개수만큼 카드를 제공한다.")
    void provideCardsAsRequested() {
        //given
        CardDeck cardDeck = new CardDeck();
        int expectedCardCount = 2;

        //when
        List<Card> drawnCards = cardDeck.drawCard(expectedCardCount);

        //then
        assertThat(drawnCards).hasSize(expectedCardCount);
    }


}