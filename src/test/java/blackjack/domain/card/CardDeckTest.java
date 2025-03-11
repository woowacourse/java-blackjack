package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.exception.ExceptionMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    CardGenerator cardGenerator = new CardGenerator();
    CardDeck cardDeck = new CardDeck(cardGenerator);

    @Test
    @DisplayName("카드덱을 생성할때 초기 카드들을 생성할 수 있다.")
    void canMakeCard() {
        cardDeck.initialize();
        assertThat(cardDeck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("원하는 횟수만큼의 카드를 제공한다.")
    void canDrawCardForHit() {
        cardDeck.initialize();
        List<Card> drawnCards = cardDeck.drawCard(5);

        assertThat(drawnCards).hasSize(5);
    }

    @Test
    @DisplayName("남은 카드가 없을 경우 예외를 발생시킨다.")
    void cannotDrawCard() {
        cardDeck.initialize();
        int tooManyCardCount = cardDeck.getSize() + 1;

        assertThatCode(() -> cardDeck.drawCard(tooManyCardCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.EMPTY_CARD_DECK.getContent());
    }
}