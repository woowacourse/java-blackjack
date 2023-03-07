package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.card.Deck.CARD_TOTAL_SIZE;

class DeckTest {

    @Test
    @DisplayName("giveFirstCard()는 카드가 비어있으면 예외를 발생시킨다.")
    void give_first_card_empty_test() {
        // given
        Deck deck = Deck.getInstance();

        // when
        for (int cardIndex = 0; cardIndex < CARD_TOTAL_SIZE; cardIndex++) {
            deck.giveFirstCard();
        }
        // then
        Assertions.assertThatThrownBy(deck::giveFirstCard)
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("뽑을 수 있는 카드가 없습니다.");
    }

}
