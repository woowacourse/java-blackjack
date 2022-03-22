package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    private static final Cards TWELVE_CARDS = new Cards(List.of(
            new Card(CardPattern.DIAMOND, CardNumber.TWO),
            new Card(CardPattern.DIAMOND, CardNumber.KING)
    ));

    private State state;

    @BeforeEach
    void setUp() {
        state = new Hit(TWELVE_CARDS);
    }

    @Test
    @DisplayName("Hit 일 때 카드를 뽑아서 총합이 21 이하이면 Hit 상태이다.")
    void hitDraw() {
        state.draw(new Card(CardPattern.HEART, CardNumber.TWO));

        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Hit 일 때 카드를 뽑아서 총합이 21 을 초과하면 Bust 상태이다.")
    void hitBustDraw() {
        state = state.draw(new Card(CardPattern.HEART, CardNumber.KING));

        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Hit 일 때 stay 하면, Stay 상태로 바뀐다.")
    void stay() {
        state = state.stay();

        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Hit 일 때 profit을 구하려고 하면, 예외가 발생한다.")
    void checkProfit() {
        final Dealer dealer = new Dealer(TWELVE_CARDS);

        assertThatThrownBy(() -> state.profit(dealer, 1000))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("실행 중인 상태에서는 수익을 계산할 수 없습니다.");
    }
}
