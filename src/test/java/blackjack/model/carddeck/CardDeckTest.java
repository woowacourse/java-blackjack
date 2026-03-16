package blackjack.model.carddeck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.error.ErrorCode;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    PickStrategy mustPickAceStrategy = cards -> Card.of(Rank.ACE, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱을 생성한다.")
    void ofCardDeck() {
        // when & then
        assertThatCode(() -> CardDeck.of(mustPickAceStrategy)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 1장 가져오기 테스트")
    void pickCardFromDeckTest() {
        // given
        CardDeck cardDeck = CardDeck.of(mustPickAceStrategy);

        // when
        Card card = cardDeck.pick();

        // then
        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("카드 덱에 카드가 없는데 뽑으려고 하면 예외가 발생")
    void emptyCardDeckExceptionTest() {
        // given
        CardDeck cardDeck = CardDeck.of(new RandomPickStrategy());
        for (int i = 0; i < 52; i++) {
            cardDeck.pick();
        }

        // when & then
        assertThatThrownBy(cardDeck::pick)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(ErrorCode.EMPTY_CARD_DECK.getMessage());
    }
}
