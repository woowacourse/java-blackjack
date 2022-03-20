package blackjack.domain.state;

import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import java.util.List;
import org.junit.jupiter.api.Test;

class InitTest {
    Cards cards = new Cards(List.of(TEN,NINE));
    Init init = new Init(cards) {
        @Override
        public State draw(Card card) {
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
        public double profit(double money) {
            return 0;
        }
    };

    @Test
    void cards() {
        assertThat(init.cards())
                .isEqualTo(cards);
    }
}
