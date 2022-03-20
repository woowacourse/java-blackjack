package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    @Test
    @DisplayName("Hit 일 때 카드를 뽑아서 총합이 21 이하이면 Hit 상태이다.")
    void hitDraw() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                new Card(CardPattern.DIAMOND, CardNumber.QUEEN)
        ));
        final State state = StateFactory.createFirstState(cards)
                .draw(new Card(CardPattern.HEART, CardNumber.TWO));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 일 때 카드를 뽑아서 총합이 21 을 초과하면 Bust 상태이다.")
    void hitBustDraw() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                new Card(CardPattern.DIAMOND, CardNumber.QUEEN)
        ));
        final State state = StateFactory.createFirstState(cards)
                .draw(new Card(CardPattern.HEART, CardNumber.KING));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 일 때 stay 하면, Stay 상태로 바뀐다.")
    void stay() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.THREE),
                new Card(CardPattern.DIAMOND, CardNumber.QUEEN)
        ));
        final State state = StateFactory.createFirstState(cards)
                .stay();

        assertThat(state).isInstanceOf(Stay.class);
    }
}
