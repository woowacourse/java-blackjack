package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class DeckTest {

    MockDeckMaker mockDeckMaker = new MockDeckMaker();

    @Nested
    @DisplayName("카드를 뽑을 때")
    class drawCard {
        @Test
        @DisplayName("첫 번째 카드를 뽑아서 반환한다.")
        void drawCard_success() {
            // given
            Deck deck = new Deck(mockDeckMaker);

            // when
            Card card = deck.drawCard();

            // then
            assertThat(card)
                    .isEqualTo(Card.of(Suit.DIAMOND, Rank.THREE));
        }

        @Test
        @DisplayName("카드가 없으면 예외가 발생해야 한다.")
        void drawCard_empty() {
            // given
            Deck deck = new Deck(mockDeckMaker);
            deck.drawCard();
            deck.drawCard();
            deck.drawCard();

            // expect
            assertThatIllegalStateException()
                    .isThrownBy(deck::drawCard)
                    .withMessage("[ERROR] 남은 카드가 없습니다.");
        }
    }

    static class MockDeckMaker implements Shuffler {
        @Override
        public List<Card> createDeck() {
            List<Card> cards = new ArrayList<>();
            cards.add(Card.of(Suit.DIAMOND, Rank.THREE));
            cards.add(Card.of(Suit.HEART, Rank.FOUR));
            cards.add(Card.of(Suit.SPADE, Rank.SIX));
            return cards;
        }
    }
}