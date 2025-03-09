package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드 덱은 초기화하면 52장이다.")
    void whenInitReturn52Size() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("요청된 개수만큼 카드를 제공한다.")
    void provideCardsAsRequested() {
        CardDeck cardDeck = new CardDeck();
        int expectedCardCount = 2;

        List<Card> drawnCards = cardDeck.drawCard(expectedCardCount);

        assertThat(drawnCards).hasSize(expectedCardCount);
    }
}