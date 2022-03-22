package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {

    private static final Cards BUST_CARDS = new Cards(List.of(
            new Card(CardPattern.SPADE, CardNumber.KING),
            new Card(CardPattern.SPADE, CardNumber.JACK),
            new Card(CardPattern.SPADE, CardNumber.SIX)
    ));
    private static final Cards TEN_CARDS = new Cards(List.of(
            new Card(CardPattern.SPADE, CardNumber.SIX),
            new Card(CardPattern.SPADE, CardNumber.FOUR)
    ));

    @Test
    @DisplayName("Bust 일 때 카드를 뽑으면 예외가 발생한다.")
    void bustDraw() {
        final State state = new Bust(BUST_CARDS);

        assertThatThrownBy(() -> state.draw(new Card(CardPattern.HEART, CardNumber.TWO)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("더 이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어가 Bust 이면, 배팅 금액을 잃는다.")
    void profitBust() {
        final State state = new Bust(BUST_CARDS);
        final Dealer dealer = new Dealer(TEN_CARDS);
        final int money = 1000;
        final double expected = money * -1;

        final double actual = state.profit(dealer, money);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Bust 상태일 때 Stay 를 하면 예외가 발생한다.")
    void checkStay() {
        final State state = new Bust(BUST_CARDS);

        assertThatThrownBy(state::stay)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("종료 상태에서는 stay를 할 수 없습니다.");
    }
}
