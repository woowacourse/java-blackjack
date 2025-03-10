package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void 카드_덱을_초기화한다() {
        // when
        CardDeck cardDeck = CardDeck.of();

        // then
        assertThat(cardDeck.getCards())
                .hasSize(52);
    }

    @RepeatedTest(100)
    void 카드가_셔플이_되었는지_확인한다() {
        // given
        CardDeck deck1 = CardDeck.of();
        CardDeck deck2 = CardDeck.of();

        // when
        List<Card> cards1 = deck1.getCards();
        List<Card> cards2 = deck2.getCards();

        // then
        assertThat(cards1).isNotEqualTo(cards2);
    }

    @Test
    void 가장_위에_있는_카드를_반환한다() {
        // given
        CardDeck deck = CardDeck.of();
        Card last = deck.getCards().getLast();

        // when
        Card card = deck.popCard();

        // then
        assertThat(card).isEqualTo(last);
    }
}
