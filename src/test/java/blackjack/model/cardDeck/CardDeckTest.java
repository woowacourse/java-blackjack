package blackjack.model.cardDeck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    PickStrategy mustPickAceStrategy =
            cards -> Card.openedCard(Rank.ACE, Suit.CLOVER);

    @Test
    @DisplayName("카드 덱을 에러 없이 생성한다.")
    void of() {
        // when & then
        assertThatCode(() -> CardDeck.of(mustPickAceStrategy)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 1장 반환한다.")
    void pick() {
        // given
        CardDeck cardDeck = CardDeck.of(mustPickAceStrategy);

        // when
        Card card = cardDeck.pick();

        // then
        assertThat(card.isAce()).isTrue();
    }
}