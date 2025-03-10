package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.exception.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    @DisplayName("카드덱을 생성할때 초기 카드들을 생성할 수 있다.")
    void canMakeCard() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("요청된 개수만큼 카드를 제공한다.")
    void canDrawCard() {
        CardDeck cardDeck = new CardDeck();
        int expectedCardCount = 2;

        List<Card> drawnCards = cardDeck.drawCard(expectedCardCount);

        assertThat(drawnCards).hasSize(expectedCardCount);
    }

    @Test
    @DisplayName("남은 카드보다 많은 카드를 요청할 경우 예외를 발생시킨다.")
    void cannotDrawCard() {
        CardDeck cardDeck = new CardDeck();
        int tooManyCardCount = 100;

        assertThatCode(() -> cardDeck.drawCard(tooManyCardCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.EMPTY_CARD_DECK.getContent());
    }
}