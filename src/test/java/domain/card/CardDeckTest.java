package domain.card;

import static domain.participant.Dealer.DECK_SIZE;

import exception.NoMoreCardException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @DisplayName("카드를 52 * 6 만큼 생성한다.")
    @Test
    void generate() {
        // given
        CardDeck cardDeck = CardDeck.generate(DECK_SIZE);

        // when && then
        Assertions.assertThat(cardDeck.size()).isEqualTo(52 * 6);
    }

    @Test
    @DisplayName("카드가 없는데 카드를 뽑을 경우 예외가 발생한다.")
    void pop() {
        //given
        CardDeck cardDeck = CardDeck.generate(1);

        //when
        int cardSize = 52;
        while (cardSize > 0) {
            cardDeck.pop();
            cardSize--;
        }

        //then
        Assertions.assertThatThrownBy(cardDeck::pop)
                .isInstanceOf(NoMoreCardException.class);
    }
}
