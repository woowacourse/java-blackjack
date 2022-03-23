package blackjack.domain.state;

import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.finished.Finished;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardsIncludedStateTest {
    Cards cards = new Cards(List.of(TEN, NINE));
    CardsIncludedState cardsIncludedState = new CardsIncludedState(cards) {
        @Override
        public State addCard(Card card) {
            return null;
        }

        @Override
        public State stay() {
            return null;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public int profit(int money, Finished finished) {
            return 0;
        }
    };

    @Test
    void cards() {
        assertThat(cardsIncludedState.cards())
                .isEqualTo(cards);
    }
}
