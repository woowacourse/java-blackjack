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
        CardDeck cardDeck = new CardDeck(makeCards());
        assertThat(cardDeck.getSize()).isEqualTo(4);
    }

    @Test
    @DisplayName("원하는 횟수만큼의 카드를 제공한다.")
    void canDrawCard() {
        CardDeck cardDeck = new CardDeck(makeCards());
        List<Card> drawnCards = cardDeck.drawCard(2);

        assertThat(drawnCards).hasSize(2);
        assertThat(drawnCards.getFirst()).isEqualTo(new Card(CardShape.HEART, CardValue.JACK));
        assertThat(drawnCards.getLast()).isEqualTo(new Card(CardShape.CLOVER, CardValue.JACK));
    }

    @Test
    @DisplayName("남은 카드가 없을 경우 예외를 발생시킨다.")
    void cannotDrawCard() {
        CardDeck cardDeck = new CardDeck(makeCards());
        int tooManyCardCount = 5;

        assertThatCode(() -> cardDeck.drawCard(tooManyCardCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.EMPTY_CARD_DECK.getContent());
    }

    public List<Card> makeCards() {
        return List.of(
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.CLOVER, CardValue.JACK),
                new Card(CardShape.SPADE, CardValue.JACK),
                new Card(CardShape.DIAMOND, CardValue.JACK)
        );
    }
}