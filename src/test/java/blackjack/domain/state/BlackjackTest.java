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

class BlackjackTest {

    private static final Cards BLACKJACK_CARDS = new Cards(List.of(
            new Card(CardPattern.CLOVER, CardNumber.KING),
            new Card(CardPattern.DIAMOND, CardNumber.ACE)
    ));
    private static final Cards JACK_TWO_CARDS = new Cards(List.of(
            new Card(CardPattern.HEART, CardNumber.JACK),
            new Card(CardPattern.HEART, CardNumber.TWO)
    ));

    @Test
    @DisplayName("Blackjack 일 때 카드를 뽑으면 예외가 발생한다.")
    void blackjackDraw() {
        final State state = new Blackjack(BLACKJACK_CARDS);

        assertThatThrownBy(() -> state.draw(new Card(CardPattern.HEART, CardNumber.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어가 blackjack, 딜러가 blackjack 이 아니면, 배팅금액 * 1.5 수익을 받는다.")
    void profitBlackjack() {
        final State state = new Blackjack(BLACKJACK_CARDS);
        final Dealer dealer = new Dealer(JACK_TWO_CARDS);
        final int money = 1000;
        final double expected = money * 1.5;

        final double actual = state.profit(dealer, money);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 blackjack, 딜러가 blackjack 이면, 수익은 0이다.")
    void profitBlackjackBlackjack() {
        final State state = new Blackjack(BLACKJACK_CARDS);
        final Dealer dealer = new Dealer(BLACKJACK_CARDS);
        final int money = 1000;
        final double expected = money * 0;

        final double actual = state.profit(dealer, money);
        assertThat(actual).isEqualTo(expected);
    }
}
