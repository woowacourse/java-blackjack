package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.fixture.CardFixture;
import blackjack.fixture.DeckFixture;

class DeckTest {

    @Test
    @DisplayName("뽑히는 카드는 중복되지 않는다")
    void randomCardsNotDuplicatedTest() {
        // given
        Deck deck = Deck.generateFrom(new RandomCardStrategy());
        List<Card> cards = new ArrayList<>();

        // when
        for (int i = 0; i < 10; i++) {
            cards.add(deck.draw());
        }

        // then
        assertThat(cards.stream().distinct().count()).isEqualTo(cards.size());
    }

    @Test
    @DisplayName("덱의 맨 위에서부터 카드를 뽑는다")
    void drawTest() {
        // given
        Deck deck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.TWO, CardNumber.THREE);

        // when
        Card first = deck.draw();
        Card second = deck.draw();
        Card third = deck.draw();

        // then
        assertThat(first).isEqualTo(CardFixture.cardOf(CardNumber.THREE));
        assertThat(second).isEqualTo(CardFixture.cardOf(CardNumber.TWO));
        assertThat(third).isEqualTo(CardFixture.cardOf(CardNumber.ACE));
    }
}
