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

class StayTest {

    private static final Cards TWELVE_CARDS = new Cards(List.of(
            new Card(CardPattern.DIAMOND, CardNumber.TWO),
            new Card(CardPattern.DIAMOND, CardNumber.KING)
    ));

    @Test
    @DisplayName("Stay 상태 일 때 카드를 뽑으면 예외를 발생한다.")
    void stayDraw() {
        final Cards cards = new Cards(List.of(
                new Card(CardPattern.DIAMOND, CardNumber.TWO),
                new Card(CardPattern.DIAMOND, CardNumber.KING)
        ));
        final State state = StateFactory.createFirstState(cards).stay();

        assertThatThrownBy(() -> state.draw(new Card(CardPattern.HEART, CardNumber.TWO)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("더 이상 카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어가 Stay 이고 딜러가 Bust 라면, 베팅금액 * 1 만큼 수익을 받는다.")
    void profitStay() {
        final State state = new Stay(TWELVE_CARDS);
        final Dealer dealer = new Dealer(new Cards(List.of(
                new Card(CardPattern.SPADE, CardNumber.KING),
                new Card(CardPattern.SPADE, CardNumber.JACK)
        )));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.SIX));

        final int money = 1000;
        final double expected = money * 1;

        final double actual = state.profit(dealer, money);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("플레이어가 Stay 이고 딜러가 Stay 라면, 카드 점수를 비교해서 수익을 계산한다.")
    void profitStayStay() {
        final State state = new Stay(TWELVE_CARDS);
        final Dealer dealer = new Dealer(new Cards(List.of(
                new Card(CardPattern.SPADE, CardNumber.KING),
                new Card(CardPattern.SPADE, CardNumber.JACK)
        )));

        final int money = 1000;
        final double expected = money * -1;

        final double actual = state.profit(dealer, money);
        assertThat(actual).isEqualTo(expected);
    }
}
